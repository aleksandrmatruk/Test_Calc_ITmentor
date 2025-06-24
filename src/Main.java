import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *   для сравнения полугодовалое решение -> https://github.com/aleksandrmatruk/Calc_edme
 */

public class Main {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 10;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println("throws Exception :)");
        }
    }

    public static String calc(String input) {
        String regex = "^(\\d+)\\s?([+\\-*/])\\s?(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат -> попробуй так -> 1 + 2");
        }

        int num1 = Integer.parseInt(matcher.group(1));
        String operator = matcher.group(2);
        int result = getResult(matcher, num1, operator);

        return String.valueOf(result);
    }

    private static int getResult(Matcher matcher, int num1, String operator) {
        int num2 = Integer.parseInt(matcher.group(3));

        if (num1 < MIN_VALUE || num1 > MAX_VALUE || num2 < MIN_VALUE || num2 > MAX_VALUE) {
            throw new IllegalArgumentException( String.format("Числа должны быть от %d до %d включительно", MIN_VALUE, MAX_VALUE));
        }

        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> getDiv(num1, num2);
            default -> throw new IllegalArgumentException("Такой оператор не существует, попробуй +,-,* или /");
        };
    }

    private static int getDiv(int num1, int num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Делить на ноль нельзя! Попробуй другие числа");
        }
        return num1 / num2;
    }
}