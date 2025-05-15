package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DealershipFileManager {

    public Dealership loadDealershipInfo() {
        Dealership dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("dealership_info.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                String name = parts[0];
                String address = parts[1];
                String phone = parts[2];
                dealership = new Dealership(name, address, phone);
                loadInventory(dealership);  // Load inventory as well!
            }
        } catch (IOException e) {
            System.out.println("Error reading dealership info: " + e.getMessage());
        }
        return dealership;
    }

    public void loadInventory(Dealership dealership) {
        if (dealership == null) return;

        List<Vehicle> vehicleList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length < 8) continue;  // defensive check
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                vehicleList.add(vehicle);
            }
            dealership.setInventory(vehicleList);
        } catch (IOException e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }
    }

    public void saveDealershipInfo(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dealership_info.txt"))) {
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving dealership info: " + e.getMessage());
        }
    }

    public void saveInventory(List<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            for (Vehicle v : vehicles) {
                String vehicleLine = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());
                writer.write(vehicleLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}