package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.StringUtils;

import java.util.Optional;

public class CustomerOperationController {
    private final VendingMachine machine;
    private static final String PLUS = "+";
    private static final String BOUNDARY_LINE = "|";
    private static final String MINUS = "-";
    private static final String DOUBLE_MINUS = "--";

    public CustomerOperationController(VendingMachine machine) {
        this.machine = machine;
    }

    public void printMachine() {
        System.out.println(StringUtils.adjustText("SUPER EKSTRA AUTOMAT", 56));
        for (int row = 0; row < machine.rowsSize(); row++) {
            for (int col = 0; col < machine.colsSize(); col++) {
                printUpperBoundaryForCell();
            }
            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printSymbolForCell(row, col);
            }

            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printProductNameForCell(row, col);
            }
            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printTrayPriceForCell(row, col);
            }
            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printLowerBoundaryForCell();
            }
            System.out.println();
        }
    }

    private void printUpperBoundaryForCell() {
        String score = StringUtils.multiplyText(MINUS, 12);
        System.out.print(PLUS + score + PLUS);
    }

    private void printSymbolForCell(int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        String symbol = tray
                .map(Tray::getSymbol)
                .orElse(DOUBLE_MINUS);
        String centeredSymbol = StringUtils.adjustText(symbol, 12);
        System.out.print(BOUNDARY_LINE + centeredSymbol + BOUNDARY_LINE);
    }

    private void printProductNameForCell(int row, int col) {
        Optional<String> productName = machine.productNameAtPosition(row, col);
        String obtainedProductName = productName
                .orElse(DOUBLE_MINUS);
        String centeredName = StringUtils.adjustText(obtainedProductName, 12);
        System.out.print(BOUNDARY_LINE + centeredName + BOUNDARY_LINE);
    }

    private void printTrayPriceForCell(int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        Long priceForTray = tray
                .map(Tray::getPrice)
                .orElse(0L);
        String formattedPrice = StringUtils.adjustPrice(priceForTray);
        String centeredPrice = StringUtils.adjustText(formattedPrice, 12);
        System.out.print(BOUNDARY_LINE + centeredPrice + BOUNDARY_LINE);
    }

    private void printLowerBoundaryForCell() {
        String score = StringUtils.multiplyText(MINUS, 12);
        System.out.print(PLUS + score + PLUS);
    }
}
