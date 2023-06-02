// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        for (; ; ) {
            System.out.println("Работает калькулятор. Для выхода нажмите q!");
            System.out.print("Введите выражение (например 1+2): (Числа от 1 до 10)-> ");

            Scanner input = new Scanner(System.in);
            String expression = input.nextLine();

            String[] quit = new String[]{expression.trim()};

            if (Objects.equals(quit[0], "q")) return;
            if (Objects.equals(quit[0], "й")) return;

            expression = expression.trim().replace(" ", "");
            String[] tokens = Parse(expression);

            if (tokens.length > 0) {

                var isRim = IsRimsk(tokens);

                if (isRim) {
                    tokens = RimtoArabic(tokens);
                    int a = Integer.parseInt(tokens[0]);
                    int b = Integer.parseInt(tokens[2]);
                    if ((a < b) & (tokens[1] == "-")) System.out.println("Invalid operands");
                        else System.out.println(ArabicToRim(calc(tokens)));
                }else{
                    System.out.println("Invalid format");
                }

                if (!isRim)
                {
                   System.out.println(calc(tokens));
                }

            } else {
                System.out.println("No operation");
            }

        }
    }

    static String calc(String[] input) {
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[2]);

        if ((a > 10) | (b > 10)) return "Числа должны быть от 0 до 10";

        int result = 0;

        switch (input[1]) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> {
                try {
                    result = a / b;
                } catch (Exception e) {
                    System.out.println("Попытка деления на ноль");
                }
            }
            default -> {
                return "Неверный оператор";
            }
        }
        return String.valueOf(result);
    }

    static String[] Parse(String input) {

        String[] ret = new String[3];

        if (input.indexOf('+') > 0) {
            String[] t = input.split("\\+");
            ret[0] = t[0];
            ret[1] = "+";
            ret[2] = t[1];
            return ret;
        }

        if (input.indexOf('-') > 0) {
            String[] t = input.split("-");
            ret[0] = t[0];
            ret[1] = "-";
            ret[2] = t[1];
            return ret;
        }
        if (input.indexOf('*') > 0) {
            String[] t = input.split("\\*");
            ret[0] = t[0];
            ret[1] = "*";
            ret[2] = t[1];
            return ret;
        }

        if (input.indexOf('/') > 0) {
            String[] t = input.split("/");
            ret[0] = t[0];
            ret[1] = "/";
            ret[2] = t[1];
            return ret;
        }

        return new String[0];
    }

    static boolean IsRimsk(String[] tokens) {
        String rim = "I II III IV V VI VII VIII IX X";

        boolean b = (rim.contains(tokens[0])) & (rim.contains(tokens[2]));
        return b;
    }

    static boolean IsArabic(String[] tokens) {
        String rim = "1 2 3 4 5 6 7 8 9 10";

        boolean b = (rim.contains(tokens[0])) & (rim.contains(tokens[2]));
        return b;
    }

    static String[] RimtoArabic(String[] token)
    {
        String[] ret = new String[3];

        switch (token[0]) {
            case "I" -> ret[0] = String.valueOf(1);
            case "II" -> ret[0] = String.valueOf(2);
            case "III" -> ret[0] = String.valueOf(3);
            case "IV" -> ret[0] = String.valueOf(4);
            case "V" -> ret[0] = String.valueOf(5);
            case "VI" -> ret[0] = String.valueOf(6);
            case "VII" -> ret[0] = String.valueOf(7);
            case "VIII" -> ret[0] = String.valueOf(8);
            case "IX" -> ret[0] = String.valueOf(9);
            case "X" -> ret[0] = String.valueOf(10);
        }

        switch (token[2]) {
            case "I" -> ret[2] = String.valueOf(1);
            case "II" -> ret[2] = String.valueOf(2);
            case "III" -> ret[2] = String.valueOf(3);
            case "IV" -> ret[2] = String.valueOf(4);
            case "V" -> ret[2] = String.valueOf(5);
            case "VI" -> ret[2] = String.valueOf(6);
            case "VII" -> ret[2] = String.valueOf(7);
            case "VIII" -> ret[2] = String.valueOf(8);
            case "IX" -> ret[2] = String.valueOf(9);
            case "X" -> ret[2] = String.valueOf(10);
        }

        ret[1] = token[1];
        return ret;
    }
    
    static  String ArabicToRim(String str)
    {
        String[] rem = {"","I","II","III","IV","V","VI","VII","VIII","IX","X","XI",
                        "XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX"};
        var s = Integer.parseInt(str);

        return  rem[s];
    }

}
