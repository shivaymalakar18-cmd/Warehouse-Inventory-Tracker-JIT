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



