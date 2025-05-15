package com.pluralsight;

import java.util.List;
import java.util.stream.Collectors;

public class UserInterface {

    private Dealership dealership;
    private DealershipFileManager fileManager = new DealershipFileManager();
    private Console console = new Console();

    public void display() {
        init();

        int choice;
        do {
            displayMenu();
            choice = console.promptForInt("");

            switch (choice) {
                case 1:
                    processVehiclesByPriceRequest();
                    break;
                case 2:
                    processVehiclesByMakeModelRequest();
                    break;
                case 3:
                    processVehiclesByYearRequest();
                    break;
                case 4:
                    processVehiclesByColorRequest();
                    break;
                case 5:
                    processVehiclesByMileageRequest();
                    break;
                case 6:
                    processVehiclesByTypeRequest();
                    break;
                case 7:
                    processAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 99);
    }

    private void init() {
        dealership = fileManager.loadDealershipInfo();
        if (dealership == null) {
            System.out.println("Dealership loading failed: null returned");
            return;
        }
        fileManager.loadInventory(dealership);
        System.out.println("Loaded dealership: " + dealership.getName());
    }

    private void displayMenu() {
        System.out.println("++++++++++++++++++++++++\n    Dealership Menu   \n++++++++++++++++++++++++");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Choose an option: ");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    // 1. Vehicles by Price Range
    private void processVehiclesByPriceRequest() {
        double min = console.promptForDouble("Enter minimum price: ");
        double max = console.promptForDouble("Enter maximum price: ");
        List<Vehicle> filtered = dealership.getAllVehicles().stream()
                .filter(v -> v.getPrice() >= min && v.getPrice() <= max)
                .collect(Collectors.toList());
        displayVehicles(filtered);
    }

    // 2. Vehicles by Make / Model
    private void processVehiclesByMakeModelRequest() {
        String make = console.promptForString("Enter make: ").toLowerCase();
        String model = console.promptForString("Enter model: ").toLowerCase();
        List<Vehicle> filtered = dealership.getAllVehicles().stream()
                .filter(v -> v.getMake().toLowerCase().equals(make) && v.getModel().toLowerCase().equals(model))
                .collect(Collectors.toList());
        displayVehicles(filtered);
    }

    // 3. Vehicles by Year Range
    private void processVehiclesByYearRequest() {
        int min = console.promptForInt("Enter minimum year: ");
        int max = console.promptForInt("Enter maximum year: ");
        List<Vehicle> filtered = dealership.getAllVehicles().stream()
                .filter(v -> v.getYear() >= min && v.getYear() <= max)
                .collect(Collectors.toList());
        displayVehicles(filtered);
    }

    // 4. Vehicles by Color
    private void processVehiclesByColorRequest() {
        String color = console.promptForString("Enter color: ").toLowerCase();
        List<Vehicle> filtered = dealership.getAllVehicles().stream()
                .filter(v -> v.getColor().toLowerCase().equals(color))
                .collect(Collectors.toList());
        displayVehicles(filtered);
    }

    // 5. Vehicles by Mileage Range
    private void processVehiclesByMileageRequest() {
        int min = console.promptForInt("Enter minimum mileage: ");
        int max = console.promptForInt("Enter maximum mileage: ");
        List<Vehicle> filtered = dealership.getAllVehicles().stream()
                .filter(v -> v.getOdometer() >= min && v.getOdometer() <= max)
                .collect(Collectors.toList());
        displayVehicles(filtered);
    }

    // 6. Vehicles by Type
    private void processVehiclesByTypeRequest() {
        String type = console.promptForString("Enter vehicle type (car, truck, SUV, van): ").toLowerCase();
        List<Vehicle> filtered = dealership.getAllVehicles().stream()
                .filter(v -> v.getVehicleType().toLowerCase().equals(type))
                .collect(Collectors.toList());
        displayVehicles(filtered);
    }

    // 7. List all vehicles
    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    // 8. Add vehicle
    private void processAddVehicleRequest() {
        int vin = console.promptForInt("Enter VIN: ");
        int year = console.promptForInt("Enter Year: ");
        String make = console.promptForString("Enter Make: ");
        String model = console.promptForString("Enter Model: ");
        String type = console.promptForString("Enter Vehicle Type: ");
        String color = console.promptForString("Enter Color: ");
        int odometer = console.promptForInt("Enter Odometer: ");
        double price = console.promptForDouble("Enter Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveInventory(dealership.getAllVehicles());

        System.out.println("Vehicle added.");
    }

    // 9. Remove vehicle
    private void processRemoveVehicleRequest() {
        int vin = console.promptForInt("Enter VIN to remove: ");
        dealership.removeVehicle(vin);
        fileManager.saveInventory(dealership.getAllVehicles());

        System.out.println("Vehicle removed if it existed.");
    }
}