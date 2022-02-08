import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;
import static java.lang.Math.toIntExact;


public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMyPhoto(String photoName, String fileName){
        SendPhoto sendPhoto = null;
        try {
            sendPhoto = new SendPhoto().setPhoto(photoName, new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sendPhoto != null;
        try {
            this.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId, int random1, int random2, int random3, int answer, int random_add_int1, int random_add_int2) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(String.valueOf(random1));
        inlineKeyboardButton1.setCallbackData("False answer");
        inlineKeyboardButton2.setText(String.valueOf(random2));
        inlineKeyboardButton2.setCallbackData("False answer");
        inlineKeyboardButton3.setText(String.valueOf(answer));
        inlineKeyboardButton3.setCallbackData("True answer");
        inlineKeyboardButton4.setText(String.valueOf(random3));
        inlineKeyboardButton4.setCallbackData("False answer");
        inlineKeyboardButton5.setText("Finish");
        inlineKeyboardButton5.setCallbackData("Finish");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        //keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow4.add(inlineKeyboardButton4);
        keyboardButtonsRow5.add(inlineKeyboardButton5);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Add two numbers:\n\n" + random_add_int1 + " + " + random_add_int2 + " = \n\nWrite your answer:").setReplyMarkup(inlineKeyboardMarkup);
    }

    public int add(int random1, int random2){
        return random1 + random2;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();

        SendMessage message = new SendMessage();
        long chat_id = update.getMessage().getChatId();
        if (update.hasMessage()) {
            int[] answers =  new int[10];
            int position = 0;

            if (command.equals("/start")) {
                message.setText("Hello, my name is Mattew \uD83D\uDE1C and I am a Bot.\nI can help you in learning math :)\n\nWith me you can practice multiplying, " +
                        "dividing, subtracting and adding numbers.\nYou can also solve problems with me!\uD83E\uDDD0\n" +
                        "Let`s jump right in!\n\nTry to push the button  /adding  and see what will happen..." +
                        "\n\nWrite  /help  if you didn`t understand what to do \uD83D\uDE03");


                //DON`T WANT TO SEND A PHOTO
                sendMyPhoto("Hi", "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGhvdG98ZW58MHx8MHx8&w=1000&q=80");
            }
            if (command.equals("/help")) {
                message.setText("I can help you to learn Math \uD83E\uDD70\n\nWith me you can solve problems, learn to count... \n\n" +
                        "How to use this bot? \uD83E\uDDD0 -  Try to enter commands below at first.\n" +
                        "Then try to solve some mathematical problems\uD83D\uDC68\u200D\uD83D\uDCBB\n\n" +
                        "When you are done, enter  /finish  command \uD83D\uDE0A");
            }

            if (command.equals("/adding")) {

                int random_1 = (int) Math.floor(Math.random() * (50 + 1) + 0);
                int random_2 = (int) Math.floor(Math.random() * (50 + 1) + 0);
                answers[position] = random_1+random_2;
                position++;
                message.setText("Add two numbers: " + random_1 + " + " + random_2 + "\n.\n.\n.\n.\n.\n.\nThe answer is ..........  " + (random_1 + random_2));
            }
            if (command.equals("/subtracting")){
                int random_1 = (int) Math.floor(Math.random() * (50 + 1) + 0);
                int random_2 = (int) Math.floor(Math.random() * (200 + 1) + 0);
                answers[position] = random_2  - random_1;
                position++;
                message.setText("Subtract two numbers: " + random_2 + " - " + random_1 + "\n.\n.\n.\n.\n.\n.\nThe answer is ..........  " + (random_2 - random_1));
            }
            if (command.equals("/dividing")){
                int random_1 = (int) Math.floor(Math.random() * (200 + 1) + 0) * 2;
                int random_2 = (int) Math.floor(Math.random() * (30 + 1) + 0) * 2;
                answers[position] = random_1 / random_2;
                position++;
                message.setText("Divide two numbers: " + random_1 + " / " + random_2 +"\n.\n.\n.\n.\n.\n.\nThe answer is ..........  " + random_1 / random_2);
            }
            if (command.equals("/multiplying")){
                int random_1 = (int) Math.floor(Math.random() * (20 + 1) + 0);
                int random_2 = (int) Math.floor(Math.random() * (20 + 1) + 0);
                answers[position] = random_1 * random_2;
                position++;
                message.setText("Multiply two numbers: " + random_1 + " * " + random_2 + "\n.\n.\n.\n.\n.\n.\nThe answer is ..........  " + random_1 * random_2);
            }
            if (command.equals("/finish")){
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < answers.length; i++) {
                    result.append(answers[i]);
                    result.append(" ");
                }
                System.out.println(result);
                message.setText("Great job! \uD83D\uDE04\n\n");
            }

            message.setChatId(update.getMessage().getChatId());
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasCallbackQuery()) {

              String call_data = update.getCallbackQuery().getData();
              long message_id = update.getCallbackQuery().getMessage().getMessageId();
              long chat_id_callback = update.getCallbackQuery().getMessage().getChatId();

            SendMessage sendMessage = new SendMessage().setChatId(chat_id_callback).setParseMode(ParseMode.MARKDOWN);

            ForwardMessage message1 = new ForwardMessage();
            if (call_data.equals("True answer")) {
                sendMessage.setText("Great!");
                //sendMessage.setChatId(message1.getChatId());
            }
            else if (call_data.equals("False answer")){
                sendMessage.setText("Practise more..");
                //sendMessage.setChatId(message1.getChatId());
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "MyMathGameBot";
    }

    @Override
    public String getBotToken() {
        return "5075193287:AAGAzPdzaikbgHsGaPFv8OCKdRnkrMtOyik";
    }
}