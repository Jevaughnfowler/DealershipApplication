package com.pluralsight;


import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private int phone;
    private List<Vehicle> inventory;


    public Dealership(String name, String address, int phone, List<Vehicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(List<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(int vin) {
        // put in later
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return null;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return null;
    }



}
