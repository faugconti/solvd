package travelAgency.service;

import travelAgency.util.ReflectionUtils;
import travelAgency.util.enums.Entities;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    static final Scanner input = new Scanner(System.in);
    private static String dataSource = "";
    private static List<Entities> entities = Arrays.asList(Entities.Customer,Entities.Employee, Entities.Excursion, Entities.Hotel, Entities.Booking); //for testing

    public static void mainMenu(){
        while (true) {
            System.out.println("1. Select Data Source");
            if (!dataSource.isEmpty()) {
                System.out.println("2. Manage Entities");
                System.out.println("3. Exit");
            }
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    selectDataSource();
                    break;
                case 2:
                    if (!dataSource.isEmpty()) selectEntityMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void selectDataSource() {
        System.out.println("Please Select your data source");
        System.out.println("1. Database");
        System.out.println("2. XML");
        System.out.println("3. JSON");
        System.out.print("Enter: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                dataSource = "Database";
                break;
            case 2:
                dataSource = "XML";
                break;
            case 3:
                dataSource = "JSON";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        System.out.println("Data source set to: " + dataSource);
    }

    private static void selectEntityMenu() {
        for (int i = 0; i < entities.size(); i++) {
            System.out.println((i + 1) + ". " + entities.get(i));
        }
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice > 0 && choice <= entities.size()) {
            Entities selectedEntity = entities.get(choice - 1);
            manageEntityMenu(selectedEntity);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void manageEntityMenu(Entities entity) {
        while (true) {
            System.out.println("1. Add " + entity);
            System.out.println("2. Update " + entity);
            System.out.println("3. Remove " + entity);
            System.out.println("4. Display Single "+entity);
            System.out.println("5. Display ALL " + entity + "s");
            System.out.println("6. Back to prev menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    ServiceFactory.getService(dataSource,entity.getEntityClass()).add();
                    break;
                case 2:
                    ServiceFactory.getService(dataSource,entity.getEntityClass()).update();
                    break;
                case 3:
                    ServiceFactory.getService(dataSource,entity.getEntityClass()).remove();
                    break;
                case 4:
                    ServiceFactory.getService(dataSource,entity.getEntityClass()).findById();
                    break;
                case 5:
                    ServiceFactory.getService(dataSource,entity.getEntityClass()).findAll();
                    break;
                case 6 :
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static int getIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static String askForInput(String message) {
        System.out.println(message);
        return input.nextLine().trim();
    }


}
