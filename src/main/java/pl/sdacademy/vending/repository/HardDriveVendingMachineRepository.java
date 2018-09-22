package pl.sdacademy.vending.repository;

import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

public class HardDriveVendingMachineRepository implements VendingMachineRepository {

    private final String fileLocalizationName;

    public HardDriveVendingMachineRepository(Configuration config) {
        fileLocalizationName = config.getProperty(
                "repository.location.vendingMachine",
                "VenMach.ser");
    }

    @Override
    public VendingMachine save(VendingMachine vendingMachine) {
        return null;
    }

    @Override
    public Optional<VendingMachine> load() {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(
                             new FileInputStream(fileLocalizationName))) {
            VendingMachine readVendingMachine =
                    (VendingMachine) objectInputStream.readObject();
            return Optional.ofNullable(readVendingMachine);
        } catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
