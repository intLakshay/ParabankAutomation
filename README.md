# ParaBank Automation Framework

Automated end-to-end regression testing framework for the [ParaBank demo banking application](https://parabank.parasoft.com/parabank/index.htm) using **Selenium WebDriver**, **Java 17**, **TestNG**, and **Maven**.

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Automation Tool:** Selenium WebDriver (4.27.0)
- **Test Framework:** TestNG (7.10.2)
- **Build & Dependency Tool:** Apache Maven
- **Browser:** Mozilla Firefox (GeckoDriver managed via Selenium Manager)

---

## 📁 Project Structure

```text
ParaBankAutomation/
├── src/
│   ├── main/java/com/parabank/
│   │   └── App.java
│   └── test/java/com/parabank/
│       ├── base/
│       │   ├── BaseTest.java                  # Browser setup/teardown & explicit wait helpers
│       │   ├── RegistrationBaseTest.java
│       │   ├── AccountOverviewBaseTest.java
│       │   ├── BillPaymentBaseTest.java
│       │   ├── FindTransactionsBaseTest.java
│       │   ├── OpenAccountBaseTest.java
│       │   ├── TransferFundsBaseTest.java
│       │   └── LogoutBaseTest.java
│       ├── tests/
│       │   ├── RegistrationTest.java
│       │   ├── LoginTest.java
│       │   ├── AccountOverviewTest.java
│       │   ├── BillPaymentTest.java
│       │   ├── FindTransactionsTest.java
│       │   ├── OpenAccountTest.java
│       │   ├── TransferFundsTest.java
│       │   ├── LogoutTest.java
│       │   └── BrowserCheck.java
│       └── utils/
│           └── TestData.java
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites
- **Java JDK 17** or higher installed and configured in your `PATH`.
- **Apache Maven 3.8+** installed.
- **Mozilla Firefox** browser installed.

### Installation
Clone the repository:
```bash
git clone https://github.com/<your-username>/ParaBankAutomation.git
cd ParaBankAutomation
```

### Run Tests
To compile and execute all test suites via Maven:
```bash
mvn clean test
```

To run a specific test class:
```bash
mvn test -Dtest=LoginTest
```

---

## 📊 Test Coverage

- **User Registration:** Creating dynamic user profiles with unique credentials.
- **User Authentication:** Login and session logout flows.
- **Account Services:**
  - Open new accounts (Savings / Checking)
  - Accounts Overview and balance validation
  - Funds transfer between accounts
  - Bill payment workflows
  - Find transactions by ID, date, date range, and amount
