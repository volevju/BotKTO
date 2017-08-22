import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.ArrayList;

public class BotKTO { // Здесь не нужен implements

    public static final String ECHO_BOT_TOKEN = "MzQ4NTUyNzgzNDgzNTAyNjAy.DHommQ.FlTi97cFCSG1iOkno7DH-O21afA";
    Calculator calculator = new Calculator();

    private IDiscordClient discordClient;
    private boolean isConnected;

    public BotKTO() {
        this.isConnected = false;
    }

    private void regBot() {
        /*
         * Получаем ссылку на диспетчер событий.
		 */
        EventDispatcher dispatcher = discordClient.getDispatcher();

		/*
         * Регистрируем этого бота в диспетчере.
		 */
        dispatcher.registerListener(this);
    }

    public void login() throws DiscordException {
        ClientBuilder cBuilder = new ClientBuilder();
        cBuilder.withToken(BotKTO.ECHO_BOT_TOKEN);
        discordClient = cBuilder.login();
        regBot();

        this.isConnected = true;
    }

    @EventSubscriber // Оказывается есть такая штука
    public void handle(MessageReceivedEvent event) {

        IMessage message = event.getMessage();
        IChannel channel = message.getChannel();
        String inputMsgStr = message.getContent();
        IUser author = message.getAuthor();
        inputMsgStr = inputMsgStr.toLowerCase();

        if(inputMsgStr.startsWith("=")) { // Если пользователь хочет произвести вычисления, то вызвать метод
            calculator.calculate(message);
        }

        try {

                /* БОТ-ПРИКОЛИСТ */
            if (inputMsgStr.contains("повтори")) {
                channel.sendMessage(inputMsgStr.replaceFirst("повтори", ""));
                System.out.println("Повторение произошло успешно. Автор был: " + author.getName());
            }
            if (inputMsgStr.equals("лол")
                    || inputMsgStr.contains(" лол")
                    || inputMsgStr.contains("лол ")
                    || inputMsgStr.contains("кек")
                    || inputMsgStr.contains("lol")) {
                message.addReaction("🎒");
                channel.sendMessage("Внимание! Школьник детектед — " + author + "!");
                message.addReaction("👎");
            }

            if (inputMsgStr.contains("привет") || inputMsgStr.contains("здравствуй")) {
                message.addReaction("👋");
            }
            if (inputMsgStr.contains("бан") || inputMsgStr.contains("вак")) {
                ArrayList<String> banWordList = new ArrayList<String>();
                banWordList.add("Если что, у меня вак за TF2");
                banWordList.add("У меня бан за игру, в которую я даже не играл!");
                banWordList.add("Мне вак за бхоп дали!");
                banWordList.add("У меня вак за скинченджер!");
                banWordList.add("А мне вак просто так дали");
                banWordList.add("Если что, у меня вак за кс 1.6");
                int caseNum = (int) (Math.random() * banWordList.size());
                channel.sendMessage(banWordList.get(caseNum));
            }
            if (inputMsgStr.contains("авп") || inputMsgStr.contains("авик") || inputMsgStr.contains("awp")) {
                ArrayList<String> awpWordList = new ArrayList<String>();
                awpWordList.add("ДАШ АВП!");
                awpWordList.add("Дай авик, а?!");
                awpWordList.add("Мне дай авик, я лучше сыграю");
                awpWordList.add("Дашь авик? Ну всё — гг...");
                awpWordList.add("У тебя драгонлор?");
                awpWordList.add("Я на ставках драгонлор выиграл недавно, но она на другом аккаунте, так что показать не могу(");
                awpWordList.add("Дай мне авик!");
                awpWordList.add("Отдай авик!");
                awpWordList.add("Дал авик, быстра!");
                awpWordList.add("Дай авп пож! Даш? Даш? Даш? Ну и пошёл на хуй пидр!");
                int caseNum = (int) (Math.random() * awpWordList.size());
                channel.sendMessage(awpWordList.get(caseNum));
            }
            if (inputMsgStr.contains("csgo") || inputMsgStr.contains("ксго") || inputMsgStr.contains("cs:go") || inputMsgStr.contains(" кс ")) {
                ArrayList<String> csgoWordList = new ArrayList<String>();
                csgoWordList.add("Нееет, с вами в ксго я не пойду, вы же раки!");
                csgoWordList.add("Нет уж, я с вами ходил в прошлый раз, так слил званку...");
                csgoWordList.add("У меня бан 3 дня ;(");
                csgoWordList.add("Какие ранги то хоть? Ниже суприма не пойду");
                csgoWordList.add("На меня не рассчитывайте, я с днищами не играю");
                csgoWordList.add("Какой вам ксго, ваш уровень это варфейс");
                int caseNum = (int) (Math.random() * csgoWordList.size());
                channel.sendMessage(csgoWordList.get(caseNum));
            }

            /* GRAMMAR-NAZI BOT */
            ArrayList<String> insultsWordList = new ArrayList<String>();
            insultsWordList.add("школьник");
            insultsWordList.add("мелкий пиздюк");
            insultsWordList.add("невежда");
            insultsWordList.add("неуч");
            insultsWordList.add("питекантроп");
            insultsWordList.add("бомж");
            insultsWordList.add("затупок");
            insultsWordList.add("школозавр");
            insultsWordList.add("серый");
            insultsWordList.add("школьник");
            insultsWordList.add("школьник");
            insultsWordList.add("школьник");
            insultsWordList.add("школьник");
            insultsWordList.add("школьник");
            insultsWordList.add("школьник");
            int caseNum = (int) (Math.random() * insultsWordList.size());

            if (inputMsgStr.contains("пошол") || inputMsgStr.contains("пришол") || inputMsgStr.contains("подошол")) {
                channel.sendMessage(author + ", правильно: **пош__ё__л**, " + insultsWordList.get(caseNum));
            }
            if (inputMsgStr.contains("приниси")) {
                channel.sendMessage(author + ", правильно: **прин__е__си**, " + insultsWordList.get(caseNum));
            }
            if (inputMsgStr.contains("играеш?") || inputMsgStr.contains("играеш ")) {
                channel.sendMessage(author + ", правильно: **играеш__ь__**, " + insultsWordList.get(caseNum));
            }
            if (inputMsgStr.contains("логает")) {
                channel.sendMessage(author + ", правильно: **л__а__гает**, " + insultsWordList.get(caseNum));
            }

        } catch (RateLimitException e) {
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            e.printStackTrace();
        } catch (DiscordException e) {
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            e.printStackTrace();
        } catch (MissingPermissionsException e) {
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // создаем бота
        BotKTO echoDiscordBot = new BotKTO();

        try {
            // подключаем бот к серверу Discord
            echoDiscordBot.login();
            System.out.println("Бот успешно подключен.");
        } catch (DiscordException e) {
            System.err.println("Ошибка при подключении бота к Discord: " + e.getMessage());
        }
    }
}

//public class calculator {
//
//    public static void main (String[] args){
//
//        String s = args[0];
//        char [] ch = s.toCharArray();
//        String str1 = "";
//        String str2 = "";
//        String operator = "";
//        char charTmp = ' ';
//        int count = 0;
//        int countFirsDigit = 0;
//        int countSecondDigit= 0;
//        double x = 0;
//        double y = 0;
//
//        while (count < ch.length - 1){
//            char c = ch[countFirsDigit];
//
//            if(Character.isDigit(c)){//проверка является ли символ числом
//                String strTmp = Character.toString(c);//преобразование char в String
//                str1 = str1.concat(strTmp);//склеивание последовательности символов
//                x = Double.parseDouble(str1);//преобразование строки чисел в double
//                countFirsDigit++;//счетчик первого числа
//                count++;//общий счетчик
//                countSecondDigit++;//счетчик второго числа
//
//            } else {
//                countSecondDigit++;
//                count = countSecondDigit;
//
//                charTmp = c;//определяем оператор
//                operator = Character.toString(charTmp);
//
//                char c2 = ch[countSecondDigit];
//
//                if(Character.isDigit(c2)){
//                    String strTmp = Character.toString(c2);
//                    str2 = str2.concat(strTmp);
//                    y = Double.parseDouble(str2);
//                }
//            }
//        }
//        if (operator.equals("*")){
//            System.out.println("= " + (x * y));
//        }
//        if (operator.equals("/")){
//            System.out.println("= " + (x / y));
//        }
//        if (operator.equals("+")){
//            System.out.println("= " + (x + y));
//        }
//        if (operator.equals("-")){
//            System.out.println("= " + (x - y));
//        }
//    }
