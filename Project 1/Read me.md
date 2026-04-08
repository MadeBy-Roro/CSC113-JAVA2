🚇 Riyadh Metro Simulation System

Java OOP – CSC 113 Project

📌 Overview

This project is an interactive metro network simulation built using Object-Oriented Programming (OOP) in Java.
It provides two main user experiences:

👩‍💼 1. Manager View

Allows the manager to:
	•	Add / remove employees
	•	Add / remove stations
	•	View transport units (Metros – Buses – On-Demand Buses)
	•	Manage maintenance
	•	Track network profit

🧍‍♀️ 2. Passenger View

Allows users to:
	•	Create and manage their account
	•	Add balance
	•	Buy and refund tickets
	•	Board & unboard vehicles
	•	Browse stations and transport units

⸻

🏗️ System Architecture

🔹 Core Classes

TransportUnit (abstract)

Base class for all vehicles
	•	id, capacity, currentPassengers
	•	Array of Passenger
	•	Methods: addPassenger(), removePassenger(), isFull(), displayPassengers()

⸻

Metro (extends TransportUnit)
	•	Additional attribute: line
	•	Override toString()

⸻

Bus (extends TransportUnit)
	•	routeNumber, driver : Employee
	•	assignDriver() validation
	•	Override toString()

⸻

BusOnDemand (extends Bus)
	•	Additional attribute: neighborhood
	•	Override toString()

⸻

Person (abstract)

Parent of Employee & Passenger
	•	name, id
	•	Basic getters + toString()

⸻

Employee (extends Person)
	•	position
	•	Works in Station or drives Bus

⸻

Passenger (extends Person)
	•	balance, ticket : Ticket
	•	Methods: buyTicket(), refundTicket(), addBalance()

⸻

Ticket
	•	price
	•	getType() → determines ticket category
	•	Override toString()

⸻

🏬 Station (implements CapacityChecker)

Represents one station in the metro network
	•	stationName, location
	•	Array of TransportUnit
	•	Array of Employee
	•	Methods include:
	•	addTransportUnit(), removeTransportUnit()
	•	assignEmployee(), removeEmployee()
	•	Recursive printing of units
	•	getIsDriverBusy()
	•	calNumOfPassengers()
	•	displayInfo()

⸻

🌐 MetroNetwork

The main controller of the system
Attributes:
	•	networkName
	•	stations[], employees[], passengers[]
	•	profit

Key functionalities:
	•	Add/remove/search station
	•	Add/remove employee & passenger
	•	Board/unboard passengers
	•	Maintenance (randomized logic)
	•	Display entire network information

-------------------
📤 Sample Output (Simplified)

Welcome to Riyadh Metro!
1. Manager Panel
2. Passenger Panel

Station: Olaya
  - Metro M01 | Line: Red | Passengers: 3
  - Bus B12 | Route: 5 | Driver: Ahmed

Passenger boarded successfully.
Network profit: 50 SAR

--------------
🧪 Features Demonstrated
	•	Inheritance
	•	Polymorphism
	•	Abstract classes & interfaces
	•	Method overriding
	•	Recursion
	•	Arrays & shifting
	•	Encapsulation
	•	Composition (Bus → Driver, Passenger → Ticket)

⸻

👩‍💻 Team Members
Reema
Ghala
Nassrah

-----------
🏁 Final Notes

This system was built to simulate a mini real-world metro network using clean OOP structure.
It can be expanded with:
	•	GUI
	•	Database
	•	Full scheduling system
	•	Smart ticketing


