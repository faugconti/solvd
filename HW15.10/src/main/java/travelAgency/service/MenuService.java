package travelAgency.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    static final Scanner input = new Scanner(System.in);
    private static String dataSource = "";
    private static List<String> entities = Arrays.asList("Customer", "Destination", "Excursion", "Hotel", "Booking"); //for testing

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
        input.nextLine(); // Consume newline

        if (choice > 0 && choice <= entities.size()) {
            String selectedEntity = entities.get(choice - 1);
            manageEntityMenu(selectedEntity);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void manageEntityMenu(String entity) {
        while (true) {
            System.out.println("1. Add " + entity);
            System.out.println("2. Update " + entity);
            System.out.println("3. Display " + entity + "s");
            System.out.println("4. Back to prev menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    TravelAgency.addEntity(dataSource, entity);
                    break;
                case 2:
                    TravelAgency.updateEntity(dataSource, entity);
                    break;
                case 3:
                    TravelAgency.displayEntities(dataSource, entity);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



}
