package view;

import entities.Manufacturer;
import entities.Souvenir;
import services.ManufacturerService;
import services.SouvenirService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final SouvenirService souvenirService = new SouvenirService();
    private final ManufacturerService manufacturerService = new ManufacturerService();

    public void showMainMenu() {
        boolean check = true;
        String mainMenu = """ 
                ----------------------------
                1 - Souvenir operations;
                2 - Manufacturer operations;
                0 - Exit;
                ----------------------------
                """;
        while (check) {
            System.out.println(mainMenu);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> showSouvenirOperations();
                case "2" -> showManufacturerOperations();
                case "0" -> check = false;
                default -> System.out.println("No such operation.");
            }
        }
    }

    public void showSouvenirOperations() {
        boolean check = true;
        String souvenirOperations = """
                ----------------------------
                1.1 - Add new souvenir;
                1.2 - Update souvenir;
                1.3 - Show souvenirs;
                1.4 - Show information about souvenirs of the given manufacturer;
                1.5 - Show information about souvenirs made in the given country;
                1.6 - Show a list of souvenirs made this years;
                1.0 - Back to main menu;
                ----------------------------
                """;
        while (check) {
            System.out.println(souvenirOperations);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1.1" -> {
                    Souvenir souvenir = createSouvenir();
                    Manufacturer manufacturer = createManufacturer();
                    souvenir.setManufacturer(manufacturer);
                    souvenirService.addSouvenir(souvenir);
                    manufacturerService.addSouvenirToList(souvenir, manufacturer);
                }
                case "1.2" -> {
                    System.out.print("Name of the souvenir that will be changed: ");
                    String souvenirName = scanner.nextLine();
                    System.out.print("Name of the manufacturer of the souvenir that will be changed: ");
                    String manufacturerName = scanner.nextLine();
                    System.out.print("Country of the manufacturer of the souvenir that will be changed: ");
                    String manufacturerCountry = scanner.nextLine();
                    System.out.println("Enter new data: ");
                    Souvenir souvenir = createSouvenir();
                    Manufacturer manufacturer = createManufacturer();
                    souvenir.setManufacturer(manufacturer);
                    souvenirService.updateSouvenir(souvenirName, manufacturerName, manufacturerCountry, souvenir);
                }
                case "1.3" -> {
                    List<Souvenir> souvenirList = souvenirService.getSouvenirList();
                    if (!souvenirList.isEmpty()) {
                        souvenirList.forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "1.4" -> {
                    System.out.print("Name of the souvenir's manufacturer: ");
                    String manufacturerName = scanner.nextLine();
                    System.out.print("Country of the souvenir's manufacturer: ");
                    String manufacturerCountry = scanner.nextLine();
                    List<Souvenir> souvenirList = souvenirService.getSouvenirListByManufacturer(manufacturerName, manufacturerCountry);
                    if (!souvenirList.isEmpty()) {
                        souvenirList.forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "1.5" -> {
                    System.out.print("Country of the souvenir's manufacturer: ");
                    String country = scanner.nextLine();
                    List<Souvenir> souvenirList = souvenirService.getSouvenirListByCountry(country);
                    if (!souvenirList.isEmpty()) {
                        souvenirList.forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "1.6" -> {
                    System.out.print("Enter years separated by commas: ");
                    String s = scanner.nextLine();
                    Set<Integer> years = Stream.of(s.split(" *, *"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());
                    Map<Integer, List<Souvenir>> map = souvenirService.getSouvenirMapByYears(years);
                    if (!map.isEmpty()) {
                        map.entrySet().forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "1.0" -> {
                    souvenirService.writeSouvenirList();
                    check = false;
                }
                default -> System.out.println("No such operation.");
            }
        }
    }

    public void showManufacturerOperations() {
        boolean check = true;
        String manufacturerOptions = """
                ----------------------------
                2.1 - Add new manufacturer;
                2.2 - Update manufacturer;
                2.3 - Show manufacturers;
                2.4 - Show information about manufacturers whose prices for souvenirs are less than the specified price;
                2.5 - Show information about manufacturers and information about all the souvenirs they produced;
                2.6 - Show information about manufacturers of a given souvenir produced in a given year;
                2.7 - Delete the given manufacturer and its souvenirs;
                2.0 - Back to main menu;
                ----------------------------
                """;

        while (check) {
            System.out.println(manufacturerOptions);
            String choice = scanner.nextLine();
            switch (choice) {
                case "2.1" -> {
                    Manufacturer manufacturer = createManufacturer();
                    manufacturerService.addManufacturer(manufacturer);
                }
                case "2.2" -> {
                    System.out.print("Name of the manufacturer that will be changed: ");
                    String manufacturerName = scanner.nextLine();
                    System.out.print("Country of the manufacturer that will be changed: ");
                    String country = scanner.nextLine();
                    System.out.println("Enter new data: ");
                    Manufacturer manufacturer = createManufacturer();
                    manufacturerService.updateManufacturer(manufacturerName, country, manufacturer);
                }
                case "2.3" -> {
                    Set<Manufacturer> manufacturerSet = manufacturerService.getManufacturerSet();
                    if (!manufacturerSet.isEmpty()) {
                        manufacturerSet.forEach(Manufacturer::printInfo);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "2.4" -> {
                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    Set<Manufacturer> manufacturerSet = manufacturerService.getManufacturerSetBySouvenirPrice(price);
                    if (!manufacturerSet.isEmpty()) {
                        manufacturerSet.forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "2.5" -> {
                    Set<Manufacturer> manufacturerSet = manufacturerService.getManufacturerSet();
                    if (!manufacturerSet.isEmpty()) {
                        manufacturerSet.forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "2.6" -> {
                    System.out.print("Souvenir's name: ");
                    String souvenirName = scanner.nextLine();
                    System.out.print("Production year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    Set<Manufacturer> manufacturerSet = manufacturerService.getManufacturerSetBySouvenirAndYear(souvenirName, year);
                    if (!manufacturerSet.isEmpty()) {
                        manufacturerSet.forEach(System.out::println);
                    } else {
                        System.out.println("List is empty.");
                    }
                }
                case "2.7" -> {
                    System.out.print("Name of the manufacturer that will be deleted: ");
                    String name = scanner.nextLine();
                    System.out.print("Country of the manufacturer that will be deleted: ");
                    String country = scanner.nextLine();
                    manufacturerService.deleteManufacturer(name, country);
                    souvenirService.deleteSouvenirsByManufacturer(name, country);
                }
                case "2.0" -> {
                    manufacturerService.writeManufacturerSet();
                    check = false;
                }
                default -> System.out.println("No such operation.");
            }
        }
    }

    public Souvenir createSouvenir() {
        System.out.print("Souvenir's name: ");
        String name = scanner.nextLine();
        System.out.print("Year of manufacture: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        return new Souvenir(name, year, price, null);
    }

    public Manufacturer createManufacturer() {
        System.out.print("Manufacturer's name: ");
        String manufacturersName = scanner.nextLine();
        System.out.print("Manufacturer's country: ");
        String manufacturersCountry = scanner.nextLine();

        return new Manufacturer(manufacturersName, manufacturersCountry, new ArrayList<>());
    }
}
