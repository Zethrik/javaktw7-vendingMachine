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
        String providedPrice = askUserForString("New tray price: ");

        try {
            Long convertedPrice = (long) (Double.parseDouble(providedPrice) * 100);
            Tray tray = Tray.builder(askUserForString("New tray symbol: "))
                    .price(convertedPrice)
                    .build();
            printErrorMessage(employeeService.addTray(tray), "Tray added");
        } catch (NumberFormatException e) {
            System.out.println("\nWrong price format");
        }
    }

    public void removeTray() {
        try {
            printErrorMessage(employeeService.removeTray(
                    askUserForString("Tray symbol: ")), "Tray removed");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nWrong tray symbol format");
        }
    }

    public void addProduct() {
        try {
            printErrorMessage(employeeService.addProducts(
                    askUserForString("Tray symbol: "),
                    askUserForString("Product name: "),
                    askUserForInteger("Number of products: ")), "Products added");
        } catch (NumberFormatException e) {
            System.out.println("\nYou must input a number");
        }
    }

    private String askUserForString(String question) {
        System.out.print(question);
        return getStringUserInput();
    }

    private Integer askUserForInteger(String question) {
        System.out.print(question);
        return getIntegerUserInput();
    }

    private String getStringUserInput() {
        return scanner.nextLine();
    }

    private Integer getIntegerUserInput() {
        return scanner.nextInt();
    }

    private void printErrorMessage(String errorMessage, String confirmationMessage) {
        if (errorMessage != null) {
            System.out.println("\n" + errorMessage);
        } else {
            System.out.println("\n" + confirmationMessage);
        }
    }
}
