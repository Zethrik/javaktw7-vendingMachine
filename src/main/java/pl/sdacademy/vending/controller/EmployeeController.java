package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.controller.services.EmployeeService;
import pl.sdacademy.vending.model.Tray;

import java.util.Scanner;

public class EmployeeController {
    private final EmployeeService employeeService;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addTray() {

        System.out.print("New tray symbol: ");
        String providedSymbol = getStringUserInput();

        System.out.print("New tray price: ");
        String providedPrice = getStringUserInput();

        try {
            Long convertedPrice = (long) (Double.parseDouble(providedPrice) * 100);
            Tray tray = Tray.builder(providedSymbol)
                    .price(convertedPrice)
                    .build();
            String errorMessage = employeeService.addTray(tray);
            if (errorMessage != null) {
                System.out.println("\n" + errorMessage);
            } else {
                System.out.println("\nTray added");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nWrong price format");
        }
    }

    public void removeTray() {
        try {
            System.out.print("Input tray symbol: ");
            String providedSymbol = getStringUserInput();
            String errorMessage = employeeService.removeTray(providedSymbol);
            if (errorMessage != null) {
                System.out.println("\n" + errorMessage);
            } else {
                System.out.println("\nTray removed");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nWrong tray symbol format");
        }
    }

    private String getStringUserInput() {
        return scanner.nextLine();
    }
}
