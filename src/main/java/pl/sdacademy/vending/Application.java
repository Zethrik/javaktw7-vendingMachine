package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationController;
import pl.sdacademy.vending.controller.services.EmployeeService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.repository.HardDriveVendingMachineRepository;
import pl.sdacademy.vending.service.DefaultEmployeeService;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;
import pl.sdacademy.vending.util.PropertiesFileConfiguration;

import java.util.Optional;
import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);
    private final CustomerOperationController customerOperationController;
    private final VendingMachine vendingMachine;

    public Application() {
        Configuration configuration = PropertiesFileConfiguration.getInstance();
        vendingMachine = new VendingMachine(configuration);
        vendingMachine.init();

        VendingMachineRepository vendingMachineRepository =
                new HardDriveVendingMachineRepository(configuration);

        EmployeeService employeeService =
                new DefaultEmployeeService(vendingMachineRepository, configuration);

        customerOperationController = new CustomerOperationController(vendingMachine);
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
                    Optional<Product> boughtProduct = vendingMachine.buyProductWithSymbol(selectedSymbol);
                    if (boughtProduct.isPresent()) {
                        System.out.println("\nYou bought: " + boughtProduct.get().getName());
                    } else {
                        System.out.println("\nSold out");
                    }
                    pause();
                    break;
                case 9:
                    System.out.println("\nBye");
                    pause();
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
