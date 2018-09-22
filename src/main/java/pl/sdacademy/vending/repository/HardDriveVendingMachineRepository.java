package pl.sdacademy.vending.repository;

import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.io.*;
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
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(fileLocalizationName))) {
           objectOutputStream.writeObject(vendingMachine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendingMachine;
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
