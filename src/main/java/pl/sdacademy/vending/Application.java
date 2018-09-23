package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationController;
import pl.sdacademy.vending.controller.EmployeeController;
import pl.sdacademy.vending.controller.services.EmployeeService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.repository.HardDriveVendingMachineRepository;
import pl.sdacademy.vending.service.DefaultEmployeeService;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;
import pl.sdacademy.vending.util.PropertiesFileConfiguration;

import java.util.Optional;
import java.util.Scanner;

public class Application {
    private final EmployeeController employeeController;
    private Scanner scanner = new Scanner(System.in);
    private final CustomerOperationController customerOperationController;

    public Application() {
        Configuration configuration = PropertiesFileConfiguration.getInstance();

        VendingMachineRepository vendingMachineRepository =
                new HardDriveVendingMachineRepository(configuration);

        EmployeeService employeeService =
                new DefaultEmployeeService(vendingMachineRepository, configuration);

        employeeController = new EmployeeController(employeeService);

        customerOperationController = new CustomerOperationController(vendingMachineRepository);
    }

    public void start() {
        int userSelection = -1;
        do {
            customerOperationController.printMachine();
            printMenu();
            userSelection = getUserInput();
            switch (userSelection) {
                case 1:
                    System.out.print("Select product number: ");
                    String selectedSymbol = scanner.nextLine();
                    Optional<Product> boughtProduct = customerOperationController.buyProduct(selectedSymbol);
                    if (boughtProduct.isPresent()) {
                        System.out.println("\nYou bought: " + boughtProduct.get().getName());
                    } else {
                        System.out.println("\nSold out");
                    }
                    pause();
                    break;
                case 9:
                    System.out.println("\nThank you for using our Super Ekstra Automat. Have a nice day!");
                    break;
                case 0:
                    startServiceMenu();
                    break;
                default:
                    System.out.println("\nInvalid selection");
                    pause();
            }
        } while (userSelection != 9);
    }

    private void printMenu() {
        System.out.println("1. Buy product");
        System.out.println("9. Exit");
        System.out.println("0. Service menu");
    }

    private void startServiceMenu() {
        int userSelection = -1;
        do {
            customerOperationController.printMachine();
            printServiceMenu();
            userSelection = getUserInput();
            switch (userSelection) {
                case 1:
                    employeeController.addTray();
                    pause();
                    break;
                case 2:
                    employeeController.removeTray();
                    pause();
                    break;
                case 3:
                case 4:
                case 5:
                    pause();
                    break;
                case 9:
                    System.out.println("\nReturning to main menu");
                    pause();
                    break;
                default:
                    System.out.println("\nInvalid selection");
                    pause();
            }
        } while (userSelection != 9);
    }

    private void printServiceMenu() {
        System.out.println("   1. Add tray");
        System.out.println("   2. Remove tray");
        System.out.println("   3. Add product to tray");
        System.out.println("   4. Remove product from tray");
        System.out.println("   5. Change price");
        System.out.println("   9. Exit service menu");
    }

    private int getUserInput() {
        System.out.print("Your selection: ");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private void pause() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }
}
