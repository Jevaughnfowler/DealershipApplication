package com.pluralsight;

import java.io.*;
import java.util.List;

public class DealershipFileManager {

    //Loads dealership information from the dealershipInfo.txt file.
    public Dealership loadDealershipInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dealershipInfo.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                return new Dealership(parts[0], parts[1], parts[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading dealership info: " + e.getMessage());
        }
        return null;
    }

    // Loads inventory from a CSV file into the dealership object
    public void loadInventory(Dealership dealership) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }
    }

    // Saves the current inventory to a CSV file
    public void saveInventory(List<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            for (Vehicle v : vehicles) {
                writer.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}
