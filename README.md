# Product Management App
This is a basic CRUD application with Java for managing products. This project is developed to understand the fundamentals of Servlet/JSP and Maven.

## Local Setup

Prerequisites:

- JDK 8 or later
- Apache Maven 3.9
- Apache Tomcat 9
- MySQL 5.7

1. Clone the repository to your local machine.
2. Navigate to the project directory and run `mvn clean package`
3. Deploy the generated `target/product_app-0.0.1-SNAPSHOT.war` to your Tomcat server
4. Access http://localhost:8080/product_app

## Directory Structure

- `src/main/java`: Contains all Java source files, including data models and servlets.
- `src/main/webapp`: Contains JSP files, CSS, and images.
- `target`: Generated by Maven, containing compiled classes and the final `.war` file for deployment.
- `pom.xml`: Project Object Model file that includes project and configuration details used by Maven.
