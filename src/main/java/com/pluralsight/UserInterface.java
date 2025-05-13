package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

        private Dealership dealership;
        private Scanner scanner;

        public void display() {
            init();  // Load dealership
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                displayMenu();
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        // processVehiclesByPriceRequest();
                        break;
                    case 2:
                        // processVehiclesByMakeModelRequest();
                        break;
                    case 3:
                        // processVehiclesByYearRequest();
                        break;
                    case 4:
                        // processVehiclesByColorRequest();
                        break;
                    case 5:
                        // processVehiclesByMileageRequest();
                        break;
                    case 6:
                        // processVehiclesByTypeRequest();
                        break;
                    case 7:
                        processAllVehiclesRequest();
                        break;
                    case 8:
                        // processAddVehicleRequest();
                        break;
                    case 9:
                        // processRemoveVehicleRequest();
                        break;
                    case 99:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (choice != 99);
        }

      

        private void displayMenu() {
            System.out.println("\n Dealership Menu ");
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
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }

        private void processAllVehiclesRequest() {
            List<Vehicle> allVehicles = dealership.getAllVehicles();
            displayVehicles(allVehicles);
        }
}
