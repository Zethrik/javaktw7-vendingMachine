package pl.sdacademy.vending.service.repositories;

import pl.sdacademy.vending.model.VendingMachine;

import java.util.Optional;

public interface VendingMachineRepository {
    VendingMachine save(VendingMachine vendingMachine);
    Optional<VendingMachine> load();

}
