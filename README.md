# Warehouse-Inventory-Tracker-JIT
this is my first project

@  Overview :- 
==============
  Ye Java console-based application ek Warehouse Inventory Management System hai, jo real-time stock tracking aur alerts provide karta hai.
  System warehouses aur products ko manage karta hai aur stock levels ke hisab se alerts generate karta hai.
  Ye project Observer pattern, multithreading, aur file handling ka practical implementation hai.


@ Features :-
==========

* Add Warehouse :
------------------
  Multiple warehouses create kar sakte ho dynamically.

* Add Product :
-----------------
  Warehouse ke andar products add kar sakte ho.
  Product ke saath ID, Name, Quantity, aur Reorder Threshold define hota hai.

* Receive Shipment (Threaded) :
--------------------------------
  Products ki quantity increase karna shipment receive karte waqt.
  Threaded implementation for parallel updates.

* Fulfill Order (Threaded) :
-----------------------------
  Products ki quantity decrease karna orders fulfill karte waqt.
  Threaded implementation ensures simultaneous order processing.

* Low Stock Alerts :
----------------------
  Agar product ka quantity threshold se kam ho jaye, to alert generate hota hai.
  Observer pattern ka use karke alerts handle kiye gaye.

* View Inventory :
--------------------
  Warehouse aur uske products ka current stock view kar sakte ho.

* File Handling :
-------------------
  Inventory state ko readable text file (data.txt) me save kar sakte ho.
  Application reload hone par previous state ko load kar sakte ho.  

@ Technologies Used :-
=====================
   Language: Java
   Data Structures: ArrayList, List
   Design Pattern: Observer Pattern
   Multithreading: For shipment & order processing
   File Handling: PrintWriter for saving readable data, ObjectInputStream (optional for serialization)

@ Project Structure :-
=======================
File Name                                                      Description

Product.java :-                                                Represents a product with ID, name, quantity, and threshold.
Warehouse.java :-	                                             Manages warehouse inventory, products, observers, and stock operations.
Main.java	 :-                                                  Entry point of the program with interactive menu for user operations.and saving or loading operations
StockObserver.java  :-                                         in this create interface for observing stock


 @ How to Run :-
===============
  Compile all files :   javac *.java
  -------------------
  
  Run the application :  java Main
  ---------------------  

  Follow the interactive menu :
  ------------------------------
  Add warehouses
  Add products to warehouses
  Receive shipments
  Fulfill orders
  View inventory
  Exit and save data
  
  Data persistence :
  -------------------
  Data will be saved to data.txt in readable format.
  Next time you run the program, it will load the previous state automatically.

  @ Design Highlights :-
  =======================

   Observer Pattern :
   -------------------
   StockObserver interface and AlertService implement alerts.
   Warehouses notify observers when stock is low.

   Multithreading :
   -----------------
   Receiving shipment and fulfilling order runs in separate threads.
   Simultaneous operations possible without blocking.

   Error Handling:
   -----------------
   Handles invalid product IDs, insufficient stock, and invalid warehouse selection gracefully.
 
   Modular Design :
   ----------------
   Clear separation between Product, Warehouse, FileHandler with Main class , and AlertService.


   @ Example Workflow :-
   =======================

   1. Add Warehouse: "Main Storage"
   2. Add Product:    ID: 101, Name: Laptop, Quantity: 10, Threshold: 5
   3. Receive Shipment: +10 units of Laptop → Total = 20
   4. Fulfill Order:   16 units → Remaining = 4 → Low stock alert triggered
   5. View Inventory: Shows current stock of all products
   6. Exit: Data saved to data.txt
