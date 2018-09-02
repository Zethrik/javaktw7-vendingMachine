package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.VendingMachine;

public class CustomerOperationController {
    private VendingMachine machine;

    public CustomerOperationController() {
        machine = new VendingMachine();
    }

    public void printMachine() {
        for (int rows = 0; rows < machine.rowsSize(); rows++) {
            for (int cols = 0; cols < machine.colsSize(); cols++) {
                System.out.print("+--------+");
            }
            System.out.println();
            for (int cols = 0; cols < machine.colsSize(); cols++) {
                char rowSymbol = (char) ('A' + rows);
                int colSymbol = cols + 1;
                System.out.print("|   " + rowSymbol + colSymbol + "   |");
            }
            System.out.println();
            for (int cols = 0; cols < machine.colsSize(); cols++) {
                System.out.print("+--------+");
            }
            System.out.println();
        }
    }
}
