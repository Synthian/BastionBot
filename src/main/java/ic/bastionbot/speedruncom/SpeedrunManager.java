package ic.bastionbot.speedruncom;

import ic.bastionbot.Constants;
import ic.bastionbot.Discord;
import ic.bastionbot.speedruncom.objects.LeaderboardResponse;
import ic.bastionbot.speedruncom.objects.RunsResponse;
import ic.bastionbot.speedruncom.objects.components.PlacedRun;
import ic.bastionbot.speedruncom.objects.components.Run;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.awt.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SpeedrunManager {

    private static final Logger logger = LoggerFactory.getLogger(SpeedrunManager.class.getName());

    private SpeedrunAPI srapi;
    private Discord discord;
    private TextChannel targetChannel;

    public SpeedrunManager(Discord discord) {
        this.discord = discord;
        this.srapi = new SpeedrunAPI();
        targetChannel = discord.getTextChannelsByName(discord.getGuildById(Constants.PROD_GUILDID), Constants.RUNS_CHANNEL).get(0);
    }

    public void checkForNewRuns(Instant last_tick) {
        srapi.fetchRecentRunsFromGame(Constants.SR_GAMEID, "verify-date", "desc","verified",  new Callback<RunsResponse>() {
            @Override
            public void onResponse(Call<RunsResponse> call, Response<RunsResponse> response) {
                if (response.body() != null) {
                    //noinspection ConstantConditions
                    List<Run> runs = response.body().getRuns();

                    for (Run r : runs) {
                        Instant timestamp = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(r.getStatus().getVerifyDate()));
                        if (timestamp.isAfter(last_tick)) {
                            if (r.getMode() == null)
                                checkPlace(r, r.getId());
                            else
                                checkPlaceWithSubcat(r, r.getId());
                        }
                        else {
                            break;
                        }
                    }
                }
                else {
                    logger.warn("fetchRecentRunsFromGame response was null.");
                }
            }

            @Override
            public void onFailure(Call<RunsResponse> call, Throwable throwable) {
                logger.warn("fetchRecentRunsFromGame call FAILED.");
            }
        });
    }

    private void checkPlace(final Run run, final String id) {
        srapi.fetchLeaderboard(Constants.SR_GAMEID, run.getCategoryResponse().getCategory().getId(), new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                //noinspection ConstantConditions
                List<PlacedRun> placedRuns = response.body().getLeaderboard().getRuns();
                int place = 0;

                for (PlacedRun pr : placedRuns) {
                    if (id.equals(pr.getRun().getId())) {
                        place = pr.getPlace();
                        break;
                    }
                }

                createPost(run, place, "");
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable throwable) {
                logger.warn("fetchLeaderboard call FAILED.");
            }
        });
    }

    private void checkPlaceWithSubcat(final Run run, final String id) {
        srapi.fetchLeaderboardWithVariable(Constants.SR_GAMEID, run.getCategoryResponse().getCategory().getId(), run.getVariableMap(), new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                //noinspection ConstantConditions
                List<PlacedRun> placedRuns = response.body().getLeaderboard().getRuns();
                int place = 0;

                for (PlacedRun pr : placedRuns) {
                    if (id.equals(pr.getRun().getId())) {
                        place = pr.getPlace();
                        break;
                    }
                }

                createPost(run, place, run.getMode());
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable throwable) {
                logger.warn("fetchLeaderboardWithVariable call FAILED.");
            }
        });
    }

    private void createPost(Run run, int place, String mode) {
        logger.info("Creating run post. Run ID: " + run.getId());

        MessageBuilder mb = new MessageBuilder();

        mb.append("**").append(run.getPlayerResponse().getPlayers().get(0).getNames().getInternational()).append("'s").append("**");
        mb.append(" run has been verified: ");
        mb.append("<").append(run.getWeblink()).append(">");

        discord.queueSendMessage(mb.build(), targetChannel);

        EmbedBuilder eb = new EmbedBuilder();

        //Add Place inline
        String place_field = Integer.toString(place);
        if (place > 0) {
            if ((place % 100) > 10 && (place % 100) < 14) {
                place_field = place_field.concat("th");
            }
            else {
                switch (place % 10) {
                    case 1:
                        place_field = place_field.concat("st");
                        break;
                    case 2:
                        place_field = place_field.concat("nd");
                        break;
                    case 3:
                        place_field = place_field.concat("rd");
                        break;
                    default:
                        place_field = place_field.concat("th");
                        break;
                }
            }
            eb.addField("Place", place_field, true);
        }

        //Add Subcat inline
        if (Arrays.asList(Constants.NG_IDS).contains(mode)) {
            eb.addField("Mode", "New Game", true);
        }
        else if (Arrays.asList(Constants.NG_PLUS_IDS).contains(mode)) {
            eb.addField("Mode", "New Game+", true);
        }

        //Add category inline
        eb.addField("Category", run.getCategoryResponse().getCategory().getName(), true);

        //Add time inline
        StringBuilder sb = new StringBuilder();
        float time = run.getTimes().getPrimaryT();
        if (time > 3600) {
            sb.append(time / 3600);
            sb.append(":");
        }
        sb.append(String.format("%02d", (int) Math.ceil(time) % 3600 / 60));
        sb.append(":");
        sb.append(String.format("%02d", (int) Math.ceil(time) % 60));
        eb.addField("Time", sb.toString(), true);

        //Add title
        eb.setAuthor(run.getPlayerResponse().getPlayers().get(0).getNames().getInternational(),
                run.getPlayerResponse().getPlayers().get(0).getWeblink(), Constants.RUN_ICON);

        //Change bar color
        eb.setColor(new Color(165, 145, 78));

        //Add SR.com link
        eb.addField("Run Page", run.getWeblink(), false);

        //Add Video Link
        eb.addField("Video Link", run.getVideos().getLinks().get(0).getUri(), false);

        //Send embed
        discord.queueSendEmbed(eb.build(), targetChannel);
    }
}
