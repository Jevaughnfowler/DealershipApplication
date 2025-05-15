# 🚗 Dealership Console Application

A Java-based terminal application for managing a car dealership's vehicle inventory. This app is designed to be used by salespeople and managers at a dealership to view, search, add, and remove vehicles, with all data persisted in a pipe-delimited `.csv` file.

---

## 📋 Features

- 🔍 Search vehicles by:
  - Price range
  - Make and model
  - Year range
  - Color
  - Mileage range
  - Type (Car, Truck, SUV, Van)
- 📃 View entire inventory
- ➕ Add a new vehicle
- ❌ Remove a vehicle
- 💾 Inventory changes are saved to file automatically

---

## 🛠 Technologies Used

- Java 17+
- Console-based UI
- File I/O with CSV (pipe-delimited format)

---

## 🧱 Project Structure
com.pluralsight/
│
├── Main.java # Entry point
├── UserInterface.java # Handles all user input/output
├── Dealership.java # Business logic and inventory management
├── DealershipFileManager.java # File I/O for reading/writing inventory
└── Vehicle.java # Data model for each vehicle

👨‍💻 Author
[Jevaughn Fowler]
📚 Year Up United Code Academy
💼 Java Developer | Problem Solver | Lifelong Learner

---

### ✅ **2. UML Class Diagram (Text-Based)**

```plaintext
+--------------------+
|     Vehicle        |
+--------------------+
| - vin: int         |
| - year: int        |
| - make: String     |
| - model: String    |
| - vehicleType: String |
| - color: String    |
| - odometer: int    |
| - price: double    |
+--------------------+
| + getters          |
| + toString()       |
+--------------------+

+--------------------------+
|       Dealership         |
+--------------------------+
| - name: String           |
| - address: String        |
| - phone: String          |
| - inventory: List<Vehicle> |
+--------------------------+
| + getVehiclesByX()       |
| + addVehicle()           |
| + removeVehicle()        |
| + getAllVehicles()       |
+--------------------------+

+------------------------------+
|    DealershipFileManager     |
+------------------------------+
| + getDealership(): Dealership |
| + saveDealership()           |
+------------------------------+

+-----------------------+
|     UserInterface     |
+-----------------------+
| - dealership: Dealership |
| + display()            |
| - init()               |
| - displayVehicles()    |
| - processRequests()    |
+-----------------------+

+--------+
|  Main  |
+--------+
| + main() |
+--------+
