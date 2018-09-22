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

        System.out.println("New tray symbol: ");
        String providedSymbol = getStringUserInput();

        System.out.println("New tray price price: ");
        String providedPrice = getStringUserInput();
        Long convertedPrice = (long) (Double.parseDouble(providedPrice) * 100);

        Tray tray = Tray.builder(providedSymbol)
                .price(convertedPrice)
                .build();

        String errorMessage = employeeService.addTray(tray);
        if (errorMessage != null) {
            System.out.println(errorMessage);
        } else {
            System.out.println("Tray added");
        }

    }

    private String getStringUserInput() {
        System.out.print("Your selection: ");
        return scanner.nextLine();
    }
}
