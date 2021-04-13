# Reading is Good Application

Simple Book Store Retail Application Demonstration.

## 1. Customer Service

Responsible for customer operations such as:

- Signing Up new User.
- Login endpoint.
- Registering New Customer.
- Checking Customer Existence from Database.

## 2. Order Service

Responsible for ordering operations such as:

- Accepting new order.
- Fetching existing orders from database by customerId.
- Fetching specific order from database by orderId.

It also contains stock service inside of the order service implementation.
Stock service has injected inside of order service because of providing transactionality of order.

## 3. Stock Service

Responsible for stocking operations such as:

- Updating Stock in order to new order.
- Checking Stock availability for new order.

### User Scenario

##### 1. SignUp with username and password. POST(http://localhost:8080/security-service/V1/sign-up)
##### 2. Login with username and password and get Token. POST(http://localhost:8080/security-service/V1/login)
##### 3. Create new customer POST(http://localhost:8080/customer-service/V1/customer/new)
##### 4. Created new order with generated customerId. POST(http://localhost:8081/order-service/V1/new)
##### 5. Check created order with generated orderId. GET(http://localhost:8081/order-service/V1/order?orderId=${orderId})
##### 6. Get all orders with customerId. GET(http://localhost:8081/order-service/V1/all?customerId=${customerId})


## Installation

* mvn clean install  
* docker-compose up -d --build 
