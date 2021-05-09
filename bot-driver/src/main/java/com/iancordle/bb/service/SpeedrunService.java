package com.iancordle.bb.service;

import com.iancordle.bb.config.SpeedrunProps;
import com.iancordle.bb.exceptions.NoPlaceException;
import com.iancordle.bb.speedrun.SpeedrunApi;
import com.iancordle.bb.speedrun.model.Leaderboard;
import com.iancordle.bb.speedrun.model.LeaderboardRun;
import com.iancordle.bb.speedrun.model.Run;
import com.iancordle.bb.speedrun.requests.LeaderboardParams;
import com.iancordle.bb.speedrun.requests.RunsParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeedrunService {
    private final static Logger LOG = LoggerFactory.getLogger(SpeedrunService.class);

    private final SpeedrunApi speedrunApi;
    private final SpeedrunProps speedrunProps;

    @Autowired
    public SpeedrunService(SpeedrunApi speedrunApi, SpeedrunProps speedrunProps) {
        this.speedrunApi = speedrunApi;
        this.speedrunProps = speedrunProps;
    }

    public List<Run> getRunsSince(ZonedDateTime timestamp) {
        RunsParams params = new RunsParams()
                .withGame(speedrunProps.getGameId())
                .withStatus("verified")
                .withOrder("verify-date")
                .withDirection("desc")
                .withEmbeds("category,players,level");
        return speedrunApi.getRuns(params)
                .stream().filter(run -> {
                    if (run.getStatus() == null || run.getStatus().getVerifyDate() == null) {
                        LOG.error("No verify date for run. Run will be ignored.");
                    }
                    return run.getStatus().getVerifyDate().isAfter(timestamp);
                })
                .collect(Collectors.toList());
    }

    public int getPlaceOf(Run run) throws NoPlaceException {
        Leaderboard leaderboard = speedrunApi.getLeaderboard(run.getGame().getId(),
                run.getCategory().getId(),
                run.getLevel() != null ? run.getLevel().getId() : null,
                run.getValues(),
                new LeaderboardParams());
        Optional<LeaderboardRun> leaderboardRun = leaderboard.getRuns().stream().filter((r) -> r.getRun().getId().equals(run.getId())).findAny();
        if (leaderboardRun.isPresent()) {
            return leaderboardRun.get().getPlace();
        }
        throw new NoPlaceException(run.getId());
    }
}
