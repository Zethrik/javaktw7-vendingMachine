package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

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

        for (int rowNumber = 0; rowNumber < maxRowsSize; rowNumber++) {
            for (int colNumber = 0; colNumber < maxColsSize; colNumber++) {
                trays[rowNumber][colNumber] = createTrayForPosition(rowNumber, colNumber);
            }
        }
    }

    private Tray createTrayForPosition(int row, int col) {
        char rowSymbol = (char) ('A' + row);
        int colSymbol = col + 1;
        String symbol = "" + rowSymbol + colSymbol;
        return new Tray(symbol);
    }

    public Long rowsSize() {
        return maxRowsSize;
    }

    public Long colsSize() {
        return maxColsSize;
    }

    public Tray trayDetailsAtPosition(int rowNumber, int colNumber) {
        return trays[rowNumber][colNumber];
    }
}
