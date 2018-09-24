package pl.sdacademy.vending.controller.services;

import pl.sdacademy.vending.model.Tray;

public interface EmployeeService {
    String addTray(Tray tray);
    String removeTray(String symbol);
    String addProducts(String symbol, String productName, Integer amount);
    String emptyTray(String symbol);
}
