import ic.bastionbot.BastionBot;
import ic.bastionbot.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Driver.class.getName());

        logger.info("BastionBot Driver loaded. Welcome!");

        //Create Bot
        BastionBot BB = new BastionBot();

        String input;
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.print("> ");
            input = in.next();
            if (input.equalsIgnoreCase("say")) {
                String message = in.nextLine();
                BB.sendServerMessage(message, Constants.SERVER_MSG_CHANNEL);
            }
            else if (input.equalsIgnoreCase("quit")) {
                in.nextLine();
                BB.quit();
                logger.info("Driver shutting down.");
                System.exit(0);
            }
        }
    }
}
