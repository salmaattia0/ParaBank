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
The `pom.xml` file defines that your project is a Maven-based Java project using Selenium, TestNG, Allure reports, and libraries for CSV, Excel, and JSON handling:
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
```
git clone https://github.com/salmaattia0/ParaBank.git
```
3. Navigate to the project directory and install dependencies:  
```
mvn install
```
---

## Test Suite Structure
The project uses **TestNG** to organize and run test scenarios. The `testng.xml` file defines the test suite and the individual test classes:
- **Login Verification** → `Tests.LoginTest`  
- **Open New Account** → `Tests.NewAccountTest`  
- **Transfer Funds Between Accounts** → `Tests.TransferTest`  
- **Bill Pay** → `Tests.BillTest`
  
---

## Running Tests
To execute all test scenarios:
```
mvn test
```
---

## Project Structure
ParaBank/
│
├── .idea/ # IntelliJ IDEA project files
├── .mvn/ # Maven wrapper files
├── allure-report/ # Generated Allure reports
├── allure-results/ # Allure test results
├── screenshots/ # Screenshots captured during test execution
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── Pages/ # Page Object classes for different web pages
│ │ │ └── utils/ # Utility classes (e.g., ExcelReader, ConfigReader, BrowserUtils)
│ │ └── resources/ # Test data files (Excel, CSV, JSON, Properties)
│ └── test/
│ └── java/
│ └── Tests/ # Test classes for each scenario
├── target/ # Maven build output
├── .gitignore # Git ignore file
├── pom.xml # Maven dependencies and plugins
└── testng.xml # TestNG suite configuration
