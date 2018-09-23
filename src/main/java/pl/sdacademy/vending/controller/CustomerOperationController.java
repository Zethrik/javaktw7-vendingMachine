package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.StringUtils;

import java.util.Optional;

public class CustomerOperationController {
    private static final String PLUS = "+";
    private static final String BOUNDARY_LINE = "|";
    private static final String MINUS = "-";
    private static final String DOUBLE_MINUS = "--";
    private static final String EMPTY = "EMPTY";
    private final VendingMachineRepository vendingMachineRepository;

    public CustomerOperationController(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    public void printMachine() {
        Optional<VendingMachine> optionalMachine = vendingMachineRepository.load();
        if (!optionalMachine.isPresent()) {
            System.out.println("No machine configured");
            return;
        }
        VendingMachine machine = optionalMachine.get();

        System.out.println(StringUtils.adjustText("SUPER EKSTRA AUTOMAT", 56));
        for (int row = 0; row < machine.rowsSize(); row++) {
            for (int col = 0; col < machine.colsSize(); col++) {
                printUpperBoundaryForCell(machine, row, col);
            }
            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printSymbolForCell(machine, row, col);
            }

            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printProductNameForCell(machine, row, col);
            }
            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printTrayPriceForCell(machine, row, col);
            }
            System.out.println();

            for (int col = 0; col < machine.colsSize(); col++) {
                printLowerBoundaryForCell(machine, row, col);
            }
            System.out.println();
        }
    }

    private void printUpperBoundaryForCell(VendingMachine machine, int row, int col) {
        String score = StringUtils.multiplyText(MINUS, 12);
        System.out.print(PLUS + score + PLUS);
    }

    private void printSymbolForCell(VendingMachine machine, int row, int col) {

        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        String symbol = tray
                .map(Tray::getSymbol)
                .orElse(DOUBLE_MINUS);
        String centeredSymbol = StringUtils.adjustText(symbol, 12);
        System.out.print(BOUNDARY_LINE + centeredSymbol + BOUNDARY_LINE);
    }

    private void printProductNameForCell(VendingMachine machine, int row, int col) {
        Optional<String> productName = machine.productNameAtPosition(row, col);
        String obtainedProductName = productName
                .orElse(EMPTY);
        String centeredName = StringUtils.adjustText(obtainedProductName, 12);
        System.out.print(BOUNDARY_LINE + centeredName + BOUNDARY_LINE);
    }

    private void printTrayPriceForCell(VendingMachine machine, int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        Long priceForTray = tray
                .map(Tray::getPrice)
                .orElse(0L);
        String formattedPrice = StringUtils.adjustPrice(priceForTray);
        String centeredPrice = StringUtils.adjustText(formattedPrice, 12);
        System.out.print(BOUNDARY_LINE + centeredPrice + BOUNDARY_LINE);
    }

    private void printLowerBoundaryForCell(VendingMachine machine, int row, int col) {
        String score = StringUtils.multiplyText(MINUS, 12);
        System.out.print(PLUS + score + PLUS);
    }

    public Optional<Product> buyProduct(String symbol) {
        Optional<VendingMachine> optionalMachine =
                vendingMachineRepository.load();
        if (optionalMachine.isPresent()) {
            VendingMachine machine = optionalMachine.get();
            Optional<Product> boughtProduct = machine.buyProductWithSymbol(symbol);
            vendingMachineRepository.save(machine);
            return boughtProduct;
        } else {
            return Optional.empty();
        }

//        return optionalMachine.map((machine) -> machine.buyProductWithSymbol(symbol))
//                .orElseGet(Optional::empty);
    }
}
