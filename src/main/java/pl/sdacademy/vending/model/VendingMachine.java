package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

public class VendingMachine {

    private final Configuration configuration;

    public VendingMachine(Configuration configuration) throws IllegalArgumentException {
        this.configuration = configuration;

        Long rows = rowsSize();
        if (rows < 1 || rows > 26) {
            throw new IllegalArgumentException();
        }

        Long cols = colsSize();
        if (cols < 1 || cols > 9) {
            throw new IllegalArgumentException();
        }
    }

    public Long rowsSize() {
        return configuration.getProperty("machine.size.rows", 6L);
    }

    public Long colsSize() {
        return configuration.getProperty("machine.size.cols", 4L);
    }
}
