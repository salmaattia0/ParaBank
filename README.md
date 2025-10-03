# ParaBank Test Automation Project

## Description
This project is an automated testing suite for a web-based application using **TestNG** and **Selenium WebDriver** with Java.  
It covers core banking functionalities of the ParaBank web application, including:  

- Account registration (precondition for all scenarios)  
- Login verification  
- Opening new checking or savings accounts  
- Transferring funds between accounts  
- Paying bills and verifying transactions  

The tests ensure the web application functions correctly and handles invalid inputs gracefully.  

---

## Technologies & Dependencies
- **Java 18** – programming language  
- **Maven** – dependency management and build automation  
- **Selenium Java 4.34.0** – web automation  
- **TestNG 7.10.2** – testing framework  
- **Allure TestNG 2.28.1** – test reporting  
- **Apache POI 5.x** – reading/writing Excel files  
- **Apache Commons CSV 1.12** – handling CSV files  
- **Gson 2.13.1** – JSON parsing  
- **Commons IO 2.20** – file operations  

**Maven Plugins Used:**  
- `maven-surefire-plugin 3.1.2` – to run TestNG tests  
- `allure-maven 2.11.2` – to generate Allure reports  

---

## Project Setup
1. Make sure **Java 18+** and **Maven** are installed.  
2. Clone the repository:  
(git clone) [https://github.com/yourusername/ParaBank.git]

3. Navigate to the project directory and install dependencies:  
```mvn install```
