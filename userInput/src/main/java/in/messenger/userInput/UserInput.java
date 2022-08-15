package in.messenger.userInput;

import java.util.Scanner;

/**
 * to get the inputs from the user
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Used to get the string inputs from the user
     */
     public static String getString() {
        return SCANNER.nextLine();
     }

    /**
     * Used to get the Long inputs from the user
     */
    public static Long getLong() {
        return SCANNER.nextLong();
    }
}