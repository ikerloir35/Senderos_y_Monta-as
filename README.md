# Senderos y Montañas - Hiking Club Management System

## Project Summary

### Purpose

This project is a comprehensive hiking club management system designed to help organize and manage hiking club activities, memberships, and excursions. The system facilitates the management of club members (standard, federated, and children), hiking excursions, registrations, and insurance information through a modern JavaFX-based interface.

### Technology Stack

- **Language**: Java 22
- **Platform**: JavaFX
- **Architecture**: MVC (Model-View-Controller)
- **Key Technologies**:
  - JavaFX 22 for the user interface
  - Hibernate 6.2.6 for database operations
  - MySQL 8.3.0 for data storage
  - FormsFX 11.6.0 for form handling
  - JUnit 5.10.0 for testing

### Features

- **Member Management**:

  - Different member types (Standard, Federated, Children)
  - Member registration and information management
  - Federation status tracking
  - Insurance management

- **Excursion Management**:

  - Excursion planning and organization
  - Trail information management
  - Activity scheduling
  - Participant tracking

- **Registration System**:

  - Excursion registration management
  - Member participation tracking
  - Insurance verification
  - Attendance records

- **User Interface**:

  - Modern and intuitive JavaFX-based interface
  - Responsive design
  - Interactive forms and controls
  - User-friendly navigation

- **Technical Features**:
  - Database integration with Hibernate
  - Data persistence and retrieval
  - Form validation and processing
  - Report generation

### Security

- **Data Security**:

  - Secure database connections
  - Data validation
  - Regular backups

- **Best Practices**:
  - Input validation
  - SQL injection prevention
  - Secure data handling

### Co-Developers

- **Lead Developer**:
  - Iker López Iribas
  - Damià Belles Sampera
- **Contributors**
  - Patricia Vallejo LLivicura

## Environment Setup

### Prerequisites

- Java Development Kit (JDK) 22 or higher
- Maven 3.6 or higher
- MySQL 8.3.0 or higher
- Git

### Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/ikerloir35/Senderos_y_Monta-as.git
   ```

2. **Environment Configuration**

   - Configure database connection in `application.properties`
   - Configure application settings

3. **Build Configuration**

   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn javafx:run
   ```

### Security Best Practices

1. **Code Security**

   - Regular security audits
   - Dependency vulnerability scanning
   - Code review process

2. **Development Workflow**
   - Feature branch development
   - Pull request reviews
   - Automated testing
   - Continuous integration

### Troubleshooting

1. **Build Issues**

   - Check Java version compatibility
   - Verify Maven dependencies
   - Clear Maven cache if needed

2. **Runtime Issues**
   - Check database connectivity
   - Verify application configuration
   - Review application logs

## License

This project is licensed under the Creative Commons CC0 1.0 Universal License. This is a public domain dedication license that allows anyone to freely use, modify, and distribute the software for any purpose, including commercial use, without any restrictions or attribution requirements.

For the full license text, see the LICENSE file.
