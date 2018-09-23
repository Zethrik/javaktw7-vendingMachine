package pl.sdacademy.vending.service;

import pl.sdacademy.vending.controller.services.EmployeeService;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;

public class DefaultEmployeeService implements EmployeeService {

    private final VendingMachineRepository vendingMachineRepository;
    private final Configuration configuration;

    public DefaultEmployeeService(VendingMachineRepository vendingMachineRepository,
                                  Configuration configuration) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.configuration = configuration;
    }

    @Override
    public String addTray(Tray tray) {
        VendingMachine vendingMachine =
                vendingMachineRepository.load().orElse(new VendingMachine(configuration));

        boolean placeWasAdded = vendingMachine.placeTray(tray);
        vendingMachineRepository.save(vendingMachine);

        if (placeWasAdded) {
            return null;
        } else {
            return "Could not add tray with symbol " + tray.getSymbol();
        }
    }

    @Override
    public String removeTray(String symbol) {
        Optional<VendingMachine> loadedMachine = vendingMachineRepository.load();

        if (loadedMachine.isPresent()) {
            VendingMachine machine = loadedMachine.get();
            Optional<Tray> removedTray = loadedMachine.get().removeTrayWithSymbol(symbol);
            if (removedTray.isPresent()) {
                vendingMachineRepository.save(machine);
                return null;
            } else {
                return "Could not remove tray";
            }
        } else {
            return "There is no vending machine";
        }
    }
}
