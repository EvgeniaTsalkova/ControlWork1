package services;

import entities.Souvenir;
import storages.SouvenirStorage;

import java.util.*;
import java.util.stream.Collectors;

public class SouvenirService {

    private final SouvenirStorage souvenirStorage = new SouvenirStorage();
    private final List<Souvenir> souvenirList = souvenirStorage.readSouvenirList();

    public void addSouvenir(Souvenir souvenir) {
        souvenirList.add(souvenir);
    }

    public List<Souvenir> getSouvenirList() {
        return souvenirList;
    }

    public void updateSouvenir(String name, String manufacturerName, Souvenir souvenir) {
        Souvenir souvenirToBeUpdated = souvenirList.stream()
                .filter(s -> s.getName().equals(name) && s.getManufacturer().getName().equals(manufacturerName))
                .findFirst().orElse(null);
        if (souvenirToBeUpdated != null) {
            souvenirToBeUpdated.setName(souvenir.getName());
            souvenirToBeUpdated.setYearOfManufacture(souvenir.getYearOfManufacture());
            souvenirToBeUpdated.setPrice(souvenir.getPrice());
        }
    }

    public List<Souvenir> getSouvenirListByManufacturer(String manufacturerName) {
        return souvenirList.stream()
                .filter(s -> s.getManufacturer().getName().equals(manufacturerName))
                .collect(Collectors.toList());
    }

    public List<Souvenir> getSouvenirListByCountry(String country) {
        return souvenirList.stream()
                .filter(s -> s.getManufacturer().getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Souvenir>> getSouvenirMapByYears(Set<Integer> yearSet) {
        return souvenirList.stream()
                .collect(Collectors.groupingBy(Souvenir::getYearOfManufacture))
                .entrySet().stream()
                .filter(entry -> yearSet.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public void deleteSouvenirsByManufacturer(String manufacturerName) {
        souvenirList.removeIf(s -> s.getManufacturer().getName().equals(manufacturerName));
    }

    public void writeSouvenirList() {
        souvenirStorage.writeSouvenirList(souvenirList);
    }
}
