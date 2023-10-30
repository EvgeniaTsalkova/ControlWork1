package services;

import entities.Manufacturer;
import entities.Souvenir;
import storages.ManufacturerStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ManufacturerService {

    private final ManufacturerStorage manufacturerStorage = new ManufacturerStorage();
    private final Set<Manufacturer> manufacturerSet = new HashSet<>(manufacturerStorage.readManufacturerList());
    private final SouvenirService souvenirService = new SouvenirService();

    public void addManufacturer(Manufacturer manufacturer) {
        if (!manufacturerSet.contains(manufacturer)) {
            manufacturerSet.add(manufacturer);
        }
    }

    public Set<Manufacturer> getManufacturerSet() {
        return manufacturerSet;
    }

    public void updateManufacturer(String manufacturerName, Manufacturer manufacturer){
        Manufacturer manufacturerToBeUpdated = manufacturerSet.stream()
                .filter(m -> m.getName().equals(manufacturerName)).findFirst().orElse(null);

        if (manufacturerToBeUpdated != null) {
            manufacturerToBeUpdated.setName(manufacturer.getName());
            manufacturerToBeUpdated.setCountry(manufacturer.getCountry());
        }
    }

    public Set<Manufacturer> getManufacturerSetBySouvenirPrice(double price) {
        return manufacturerSet.stream()
                .flatMap(m -> m.getSouvenirList().stream())
                .filter(souvenir -> souvenir.getPrice() < price)
                .map(Souvenir::getManufacturer)
                .collect(Collectors.toSet());
    }

    public Set<Manufacturer> getManufacturerSetBySouvenirAndYear(String souvenirName, int year) {
        return manufacturerSet.stream()
                .flatMap(m -> m.getSouvenirList().stream())
                .filter(s -> s.getName().equals(souvenirName) && s.getYearOfManufacture() == year)
                .map(Souvenir::getManufacturer)
                .collect(Collectors.toSet());
    }

    public void deleteManufacturer(String manufacturerName) {
        manufacturerSet.removeIf(m -> m.getName().equals(manufacturerName));
        souvenirService.deleteSouvenirsByManufacturer(manufacturerName);
    }

    public void addSouvenirToList(Souvenir souvenir, Manufacturer manufacturer) {
        if (!manufacturerSet.contains(manufacturer)) {
            manufacturer.getSouvenirList().add(souvenir);
            manufacturerSet.add(manufacturer);
        } else {
            manufacturerSet.stream()
                    .filter(m -> Objects.equals(m.getName(), manufacturer.getName()))
                    .findAny().ifPresent(m -> m.getSouvenirList().add(souvenir));
        }
        writeManufacturerSet();
    }

    public void writeManufacturerSet() {
        manufacturerStorage.writeManufacturerList(new ArrayList<>(manufacturerSet));
    }
}
