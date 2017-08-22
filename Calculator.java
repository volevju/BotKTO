import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Calculator {

    /**
     * Это конструктор, он выполняет своё тело каждый раз,
     * когда создаётся новый экземпляр этого класса. В его параметрах можно
     * что-нибудь передать, например, экземпляр сообщения, чтобы можно было
     * достать оттуда сам текст, то есть inputMsgStr и обработать его
     */
    public Calculator() {

    }

    /**
     * Этот метод просто производит вычисления. Принимает экземпляр message, имеющий
     * тип IMessage, чтобы оттуда достать сам текст сообщения. Можно было ограничиться
     * только текстом типа String, но вдруг пригодятся какие-либо переменные экземпляра,
     * например, кто был автором сообщения или есть ли реакции на нём.
     */
    public void calculate(IMessage message) {

        try {
            String value = message.getContent().replaceFirst("=", "");
//                int number1 = Integer.parseInt(value);
            int number1 = 0;
            int number2 = 0;
            for (String retval : value.split("-")) {
                if (number1 == 0) {
                    number1 = Integer.parseInt(retval);
                } else {
                    number2 = Integer.parseInt(retval);
                }
            }

            if (number2 != 0) {
                int result = number1 - number2;
                message.getChannel().sendMessage("Ответ равен: " + Integer.toString(result));

                System.out.println("Калькулятор готов. Value = " + value);
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
}
