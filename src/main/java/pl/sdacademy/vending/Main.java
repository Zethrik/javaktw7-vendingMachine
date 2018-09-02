package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationController;
import pl.sdacademy.vending.util.Configuration;

public class Main {
    public static void main(String[] args) {
        new CustomerOperationController().printMachine();
        new Configuration();
    }
}
