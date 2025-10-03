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
**ParaBank/**
* **.idea/**: IntelliJ IDEA project files
* **.mvn/**: Maven wrapper files
* **allure-results/**: Allure test results (Generated after test execution)
* **allure-report/**: Generated Allure reports
* **screenshots/**: Screenshots captured during test execution
* **src/**
    * **main/**
        * **java/**
            * **Pages/**: Page Object Model classes for different web pages
            * **utils/**: Utility classes (e.g., ExcelReader, ConfigReader, BrowserUtils)
    * **resources/**: Test data files (Excel, CSV, JSON, Properties) for Data-Driven Testing (DDT)
    * **test/**
        * **java/**
            * **Tests/**: Test classes for each scenario
* **target/**: Maven build output
* **.gitignore**: Git Ignore file
* **pom.xml**: Maven dependencies and plugins
* **testng.xml**: TestNG suite configuration
  
---

## Data Resources for Data-Driven Testing (DDT)
his project is configured to use Data-Driven Testing (DDT), allowing test scenarios to be executed multiple times using different sets of input values read from external files. All data files are stored under the `src/resources/` directory.

We utilize various file formats to demonstrate robust data handling capabilities:

| File Type | Purpose | Scenario Example |
| :--- | :--- | :--- |
| **Properties** | Stores key-value pairs, typically for static configurations or simple, fixed data sets. | Login Credentials |
| **CSV** | Stores tabular data with a simple structure, ideal for varied input combinations. | Open New Account |
| **JSON** | Stores complex, structured, array-based data, excellent for iteration and testing multiple test cases. | Funds Transfer Validation |
| **Excel (XLSX)** | Stores large or complex tabular data, used when dealing with numerous columns or test cases. | Bill Pay Functionality |

---

## Pages (Page Object Model)
This project follows the **Page Object Model (POM)** design pattern to improve **readability, maintainability, and reusability** of test code.
- Each page in the ParaBank application is represented as a **Java class**.  
- Classes contain **locators** (to identify elements) and **methods** (to interact with them).  
- This allows tests to call simple methods instead of duplicating locators everywhere.  

### Implemented Page Classes:

| Page Class        | Purpose              | Key Actions |
| :--- | :--- | :--- |
| **LoginPage.java** | User authentication       | Enter credentials, Login, Validate messages, Logout |
| **NewAccountPage.java** | Open new bank accounts  | Navigate, Select account type, Choose funding account, Create account |
| **TransferPage.java** | Transfer funds between accounts | Navigate, Enter amount, Select accounts, Confirm transfer |
| **BillPage.java** | Pay bills to different payees | Navigate, Enter payee details, Send payment, Validate confirmation/error |

---

## Utilities  
This project includes several **utility classes** to support data-driven testing and improve test execution.  
They handle reading external data, managing configurations, and providing reusable helpers.  

| Utility Class        | Purpose | Key Features |
| :--- | :--- | :--- |
| **PropertiesReader** | Reads configuration values from `.properties` files | Load properties (credentials) and fetch values using `getProperty(key)` |
| **CSVReader**        | Reads data from `.csv` files | Provides data for TestNG `@DataProvider` (New Account test scenarios) |
| **JSONReader**       | Reads structured data from `.json` files | Uses Gson to parse JSON and supply objects for TestNG `@DataProvider` (Transfer Funds test scenarios) |
| **ExcelReader**      | Reads test data from `.xlsx` files | Uses Apache POI to extract rows/columns for DDT (Bill Payment test scenarios) |

---

## Test Base
The **BaseTest.java** class is responsible for setting up and tearing down the WebDriver before and after each test case.  

### Key Responsibilities:
- Initializes the **Firefox WebDriver**.
- Maximizes the browser window and navigates to the ParaBank URL.
- Captures a **screenshot** automatically when a test fails and stores it in the `./screenshots/` directory.
- Ensures that the browser is closed after each test to avoid resource leaks.

---

## How to Add New Tests
To add new automated tests for the ParaBank application, follow these steps:

### Steps:
1. **Create a new test class** inside the `tests/` directory.  
   - Extend from `BaseTest` to inherit WebDriver setup and teardown.  

2. **Create or update a Page Class** inside the `pages/` directory.  
   - Each page object should encapsulate locators and methods for interacting with page elements.  

3. **Add Test Data** inside the `resources/` directory.  
   - Use `.properties`, `.csv`, `.json`, or `.xlsx` files depending on the test.  
   - Utilities like `PropertiesReader`, `CSVReader`, `JSONReader`, and `ExcelReader` handle reading these files.  

4. **Update `testng.xml`** if you want the new test class to be included in the test suite execution.  

---

## Preconditions and Test Scenarios

### Precondition: Account Registration
Before executing any test scenarios, a valid user account must exist.

**Steps:**
1. Navigate to ParaBank homepage.  
2. Click **Register**.  
3. Fill all required fields:  
   - First Name, Last Name  
   - Address, City, State, Zip Code  
   - Phone Number, SSN  
   - Username, Password, Confirm Password  
4. Click **Register**.  
5. Validate confirmation message:  
   _“Your account was created successfully. You are now logged in.”_  
6. Log out to prepare for subsequent scenarios.  

> **Note:** This step is a precondition, not a test case.  
> User accounts are periodically removed, so automation creates new accounts as needed.  

---

### Test Scenarios

#### **Scenario 1: Login**
- **Objective:** Verify login functionality with valid and invalid credentials.  
- **Steps:**
  1. Navigate to ParaBank login page.  
  2. Enter username and password.  
  3. Click **Login**.  
  4. Validate:  
     - Successful login → redirected to Accounts Overview.  
     - Invalid/empty input → proper error messages are shown.  

---

#### **Scenario 2: Open New Account**
- **Objective:** Verify that a user can open a new account.  
- **Steps:**
  1. Login with valid credentials.  
  2. Navigate to **Open New Account** page.  
  3. Select account type and funding account.  
  4. Click **Open New Account**.  
  5. Validate that new account is created successfully.  

---

#### **Scenario 3: Transfer Funds**
- **Objective:** Verify that funds can be transferred between accounts.  
- **Steps:**
  1. Login with valid credentials.  
  2. Navigate to **Transfer Funds** page.  
  3. Enter transfer amount.  
  4. Select source and destination accounts.  
  5. Click **Transfer**.  
  6. Validate confirmation message includes amount, from-account, and to-account.  

---

#### **Scenario 4: Bill Payment**
- **Objective:** Verify that bills can be paid using valid payee details.  
- **Steps:**
  1. Login with valid credentials.  
  2. Navigate to **Bill Pay** page.  
  3. Enter all payee details (name, address, city, state, zip, phone, account, amount).  
  4. Click **Send Payment**.  
  5. Validate confirmation message indicates bill payment completion.  

---
