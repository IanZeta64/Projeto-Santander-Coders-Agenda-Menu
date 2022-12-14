package br.com.ada.agenda;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUIHelper {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String askSimpleInput(String message) {
            System.out.printf("%-79s#%n# :", message);
            return new Scanner(System.in).nextLine().trim();

    }

    public static String askNoEmptyInput(String message, int retries) {
        System.out.printf("%s%n# : ", message);
        Scanner sc = new Scanner(System.in);
        String input;
        int tries = 0;
        do {
            input = sc.nextLine().trim();
            tries++;
        } while (input.isBlank() && retries > 0 && tries < retries);
        return input;
    }

    public static int askChooseOption(String message, String... options) {
//        int msgLen = message.length();
//        int width = 80 - msgLen;
//        ;
        System.out.printf("%-79s#%n# :", message);
        for (int i = 0; i < options.length; i++) {
//            if(options.length - i != 1) {
                if (i < 9) System.out.printf("%d - %-72s#%n# :", i + 1, options[i]);
                else if (i < 99) System.out.printf("%d - %-71s#%n# :", i + 1, options[i]);
                else if (i < 999) System.out.printf("%d - %-70s#%n# :", i + 1, options[i]);
                else if (i < 9999) System.out.printf("%d - %-69s#%n# :", i + 1, options[i]);
//            }else{
//                if (i < 9) System.out.printf("%d - %-72s#%n", i + 1, options[i]);
//                else if (i < 99) System.out.printf("%d - %-71s#%n", i + 1, options[i]);
//                else if (i < 999) System.out.printf("%d - %-70s#%n", i + 1, options[i]);
//                else if (i < 9999) System.out.printf("%d - %-69s#%n", i + 1, options[i]);
//            }

        }
//        drawLine(80);
//        System.out.printf("# :%77s%n", "#");
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            try {
                choose = sc.nextInt();
                if (choose < 1 || choose > options.length){
                    System.out.println("Valor fora do indice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor numerico inteiro.");
                choose = -9;
                sc.nextLine();
            }

        } while (choose < 1 || choose > options.length);
        return choose;
    }

    public static boolean askConfirm(String message, String yes, String no) {
        String[] op = new String[2];
        op[0] = yes;
        op[1] = no;
        return askChooseOption(message, yes, no) == 1;
    }

    public static BigDecimal askNumber(String message) {
        System.out.printf("%s%n# : ", message);
        Scanner sc = new Scanner(System.in);
        BigDecimal number;
        do {
            try {
                number = sc.nextBigDecimal();
            } catch (InputMismatchException e) {
                number = null;
            }
        } while (number == null);
        return number;
    }

    public static int drawWithRightPadding(String text, int width, char pad) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), width - 4);
            String row = text.substring(0, limit);
            text = text.substring(row.length());
            row = "# " + row + String.valueOf(pad).repeat(width - row.length() - 4) + " #";
            System.out.println(row);
            count++;
        } while (!text.isEmpty());
        return count;
    }

    public static int drawWithPadding(String text, int width) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), width - 4);
            String row = text.substring(0, limit);
            text = text.substring(row.length());
            int padding = (width - row.length()) / 2;
            row = "#" + " ".repeat(padding-1) + row;
            row = row + " ".repeat(width - row.length() - 1) + "#";
            System.out.println(row);
            count++;
        } while (!text.isEmpty());
        return count;
    }

    public static void drawHeader(String title, int width) {
        drawLine(width);
        drawWithPadding(title, width);
        drawLine(width);
    }

    public static void drawLine(int width) {
        System.out.println("#".repeat(width));
    }

    public static void fillVSpace(int lines, int width) {
        drawWithPadding(" ".repeat(lines * width), width);
    }

    public static String columnPaddingLeft(String text, int width, char pad) {
        while(text.length() < width){
            text = String.valueOf(pad).concat(text);
        }
        return text;
    }

    public static String columnPaddingRight(String text, int width, char pad) {
        while(text.length() < width){
            text = text.concat(String.valueOf(pad));
        }
        return text;
    }

}