package bot;

import api.geo.GeocodingData;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public abstract class Main {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Not enough parameters. Please provide GeoCoding-API-Key as first parameter and " +
                    "Telegram-Bot-Token as second parameter.");
            System.exit(1);
        }

        GeocodingData.setApiKey(args[0]);

        // Initialize telegram bot api.
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            // Register bot.
            botsApi.registerBot(new GasStationBot(args[1]));
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Could not register Bot. Exiting program.");
            System.exit(1);
        }
    }
}
