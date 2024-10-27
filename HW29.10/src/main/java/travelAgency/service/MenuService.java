package travelAgency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.JdbcDAO;
import travelAgency.DAO.MyBatis.MyBatisDAO;
import travelAgency.util.ReflectionUtils;
import travelAgency.util.enums.Entities;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    static final Scanner input = new Scanner(System.in);
    private static String dataSource = "";
    private static String daoStrategy = ""; //default dao strategy
    private static List<Entities> entities = Arrays.asList(Entities.Customer,Entities.Employee, Entities.Excursion, Entities.Hotel, Entities.Booking); //for testing
    private static final Logger logger = LogManager.getLogger(MenuService.class);

    public static void mainMenu(){
        while (true) {
            System.out.println("1. Select Data Source");
            if (!dataSource.isEmpty()) {
                System.out.println("2. Select DAO Strategy");
                System.out.println("3. Manage Entities");
                System.out.println("4. Exit");
            }
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    selectDataSource();
                    break;
                case 2:
                    selectDAOStrategy();
                    break;
                case 3:
                    if (!dataSource.isEmpty() && !daoStrategy.isEmpty()) selectEntityMenu();
                    else logger.warn("You need to choose a Data source and DAO Strategy first");
                    break;
                case 4:
                    System.exit(0);
                default:
                    logger.warn("Invalid choice. Please try again.");
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
                logger.warn("Invalid choice. Please try again.");
                return;
        }
        logger.info("Data source set to: {}", dataSource);
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
            logger.warn("Invalid choice. Please try again.");
        }
    }

    private static void selectDAOStrategy() {
        System.out.println("Please Select your DAO strategy");
        System.out.println("1. JDBC");
        System.out.println("2. MyBatis");
        System.out.print("Enter: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                daoStrategy = "jdbc";
                break;
            case 2:
                daoStrategy = "mybatis";
                break;
            default:
                logger.warn("Invalid choice. Please try again.");
                return;
        }
        logger.info("DAO strategy set to: {}", daoStrategy);
    }


    private static void manageEntityMenu(Entities entity) {
        while (true) {
            System.out.println("1. Add " + entity);
            System.out.println("2. Update " + entity);
            System.out.println("3. Remove " + entity);
            System.out.println("4. Find " + entity);
            System.out.println("5. Display ALL " + entity + "s");
            System.out.println("6. Back to prev menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            Services<?> service = ServiceFactory.getService(dataSource,entity.getEntityClass());

            DAO<?> daoImpl = switch (daoStrategy) {
                case "jdbc" -> new JdbcDAO<>(entity.getEntityClass());
                case "mybatis" -> new MyBatisDAO<>(entity.getEntityClass());
                default -> throw new IllegalStateException("Invalid DAO strategy");
            };
            ((AbstractService<?>) service).setDAOStrategy(daoImpl);

            switch (choice) {
                case 1:
                    service.add();
                    break;
                case 2:
                    service.update();
                    break;
                case 3:
                    service.remove();
                    break;
                case 4:
                    service.findById();
                    break;
                case 5:
                    service.findAll();
                    break;
                case 6 :
                    return;
                default:
                    logger.warn("Invalid choice. Please try again.");
            }
        }
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

    public static String askForInput(String message) {
        System.out.println(message);
        return input.nextLine().trim();
    }
}
