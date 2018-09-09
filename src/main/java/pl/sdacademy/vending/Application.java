package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationController;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.Configuration;
import pl.sdacademy.vending.util.PropertiesFileConfiguration;

import java.util.Optional;

public class Application {
    private final CustomerOperationController customerOperationController;
    private final VendingMachine vendingMachine;

    public Application() {
        Configuration configuration = PropertiesFileConfiguration.getInstance();
        vendingMachine = new VendingMachine(configuration);
        customerOperationController = new CustomerOperationController(vendingMachine);
    }

    public void start() {
        customerOperationController.printMachine();
    }
}
