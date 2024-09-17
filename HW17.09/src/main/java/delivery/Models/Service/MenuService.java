package delivery.Models.Service;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import delivery.enums.MenuOption;


public class MenuService {

    private static final Logger logger = LogManager.getLogger(MenuService.class);
    static final Scanner input = new Scanner(System.in);

    public static void printMenu(MenuOption option) {
        switch (option) {
            case MAIN:
                System.out.println("1- Add new employee");
                System.out.println("2- Add new customer");
                System.out.println("3- Manage orders");
                System.out.println("4- Show company information");
                System.out.println("5- Exit");
                System.out.println();
                break;
            case ADD_ORDER:
                System.out.println("1- Add new order");
                System.out.println("2- Pay for order");
                System.out.println("3- Remove order");
                System.out.println("4- Return to main menu");
                break;
            case ADD_CUSTOMER:
                System.out.println("1- Search all customers");
                System.out.println("2- Search by name");
                System.out.println("3- Return to main menu");
                break;
            case PAY_ORDER:
                System.out.println("1- Search all draft orders");
                System.out.println("2- Search by order ID");
                System.out.println("3- Return to main menu");
                break;
            default:
                System.out.println("Wrong option, try again");
                MenuService.printMenu(MenuOption.MAIN);
                break;

        }
    }

    public static String getAnswer() {
        return input.nextLine();
    }

    public static String askForInput(String message) {
        System.out.println(message);
        return input.nextLine().trim();
    }

    public static int getIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                logger.warn("Invalid input. Please enter a number.");
            }
        }
    }

    public static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                logger.warn("Invalid input. Please enter a number.");
            }
        }
    }

}
