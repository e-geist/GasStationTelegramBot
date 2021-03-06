package bot;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public final class GasStationBot extends TelegramLongPollingBot {


    public static final String BOT_NAME = "TankstellenBot";

    private final String botToken;

    public GasStationBot(final String botToken)
    {
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText());
            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId()).setText("Test! Helloo");

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

