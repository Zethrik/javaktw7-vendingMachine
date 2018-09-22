package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Random;

public class VendingMachine {

    private final Long maxRowsSize;
    private final Long maxColsSize;
    private final Tray[][] trays;


    public VendingMachine(Configuration configuration) {
        maxRowsSize = configuration.getProperty("machine.size.rows", 6L);
        if (maxRowsSize < 1 || maxRowsSize > 26) {
            throw new IllegalArgumentException("VendingMachine can not be created with " + maxRowsSize + " rows");
        }
        maxColsSize = configuration.getProperty("machine.size.cols", 4L);
        if (maxColsSize < 1 || maxColsSize > 9) {
            throw new IllegalArgumentException("VendingMachine can not be created with " + maxColsSize + " cols");
        }
        trays = new Tray[maxRowsSize.intValue()][maxColsSize.intValue()];
    }

    public void init() {
        for (int rowNumber = 0; rowNumber < maxRowsSize; rowNumber++) {
            for (int colNumber = 0; colNumber < maxColsSize; colNumber++) {
                trays[rowNumber][colNumber] = createTrayForPosition(rowNumber, colNumber);
            }
        }
    }

    private Tray createTrayForPosition(int row, int col) {
        if (!shouldGenerateTray()) {
            return null;
        }

        char rowSymbol = (char) ('A' + row);
        int colSymbol = col + 1;
        String symbol = "" + rowSymbol + colSymbol;

        Random randomPrice = new Random();
        int generatedPrice = randomPrice.nextInt(401);   // values from 0 to 400
        int calculatedPrice = generatedPrice + 100; // changed spectrum to 100-500

        double productProbability = Math.random();
        Tray.Builder trayBuilder = Tray.builder(symbol).price((long) calculatedPrice);

        if (productProbability < 0.5) {
            Product product = new Product("Product " + symbol);
            trayBuilder = trayBuilder.products(product);
        }

        if (productProbability < 0.1) {
            Product product = new Product("Product " + symbol);
            trayBuilder = trayBuilder.products(product);
        }
        return trayBuilder.build();
    }

    private boolean shouldGenerateTray() {
        return Math.random() < 0.8;
    }

    public Long rowsSize() {
        return maxRowsSize;
    }

    public Long colsSize() {
        return maxColsSize;
    }

    public Optional<Tray> trayDetailsAtPosition(int rowNumber, int colNumber) {
        Tray obtainedTray = trays[rowNumber][colNumber];
        Optional<Tray> tray = Optional.ofNullable(obtainedTray);
        return tray;
    }

    public Optional<String> productNameAtPosition(int rowNumber, int colNumber) {
        Tray tray = trays[rowNumber][colNumber];
        if (tray != null) {
            return tray.firstProductName();
        } else {
            return Optional.empty();
        }
    }

    public Optional<Product> buyProductWithSymbol(String symbol) {
        Optional<Tray> tray = getTrayForSymbol(symbol);
        if (tray.isPresent()) {
            return tray.get().getFirstProduct();
        } else {
            return Optional.empty();
        }
    }


    public Optional<Tray> getTrayForSymbol(String symbol) {
        int rowNumber = getRowNomberForSymbol(symbol);
        int colNumber = getColNumberForSymbol(symbol);
        return trayDetailsAtPosition(rowNumber, colNumber);
    }

    public boolean placeTray(Tray tray) {
        String symbol = tray.getSymbol();
        int rowNumber = getRowNomberForSymbol(symbol);
        int colNumber = getColNumberForSymbol(symbol);
        trays[rowNumber][colNumber] = tray;
        return true;
    }

    private int getColNumberForSymbol(String symbol) {
        char colSymbol = symbol.charAt(1);
        return colSymbol - '1';
    }

    private int getRowNomberForSymbol(String symbol) {
        char rowSymbol = symbol.toUpperCase().charAt(0);
        return rowSymbol - 'A';
    }
}
