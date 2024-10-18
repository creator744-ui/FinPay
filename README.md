# FinPay

FinPay is a comprehensive financial services platform designed to facilitate various payment processes, virtual card management, invoicing, reporting, and more. It consists of a modular microservices architecture that enables independent development, deployment, and scaling of different services.

## Project Structure

The FinPay project is organized as follows:

```
FinPay/
├── README.md                        # Project Documentation
├── docs/                            # Documentation
│   ├── architecture/                # System Architecture documentation
│   ├── api-specifications/          # API Specifications
│   └── user-guides/                 # User Guides and Manuals
├── common/                          # Shared Libraries & Utilities
│   ├── libs/                        # Shared libraries across services
│   ├── models/                      # Shared data models
│   └── utils/                       # Utility functions and helpers
├── infrastructure/                  # Infrastructure as Code
│   ├── terraform/                   # Terraform scripts for cloud infrastructure
│   │   ├── network/                 # Networking configurations
│   │   ├── databases/               # Database provisioning
│   │   └── kubernetes/              # Kubernetes cluster configurations
│   └── kubernetes/                  # Kubernetes configurations
│       ├── namespaces/              # Namespace definitions
│       ├── secrets/                 # Secret management
│       └── configmaps/              # Configuration maps
├── ci-cd/                           # Continuous Integration & Deployment
│   ├── azure-pipelines.yml          # Azure Pipelines configuration
│   └── scripts/                     # Helper scripts for build, deploy, and test
│       ├── build.sh
│       ├── deploy.sh
│       └── test.sh
├── api-gateway/                     # API Gateway Service
│   ├── src/                         # Source code
│   ├── Dockerfile                   # Dockerfile for containerization
│   ├── pom.xml                      # Maven configuration
│   └── helm/                        # Helm charts for deployment
├── authentication-service/          # Authentication & Authorization Service
├── user-service/                    # User Management Service
├── invoice-service/                 # Invoice Management Service
├── payment-service/                 # Payment Processing Service
├── virtual-card-service/            # Virtual Card Management Service
├── virtual-account-service/         # Virtual Account Management Service
├── reporting-service/               # Financial Reporting Service
├── notification-service/            # Notification Service
├── config-service/                  # Central Configuration Management
├── discovery-service/               # Service Discovery
├── message-broker/                  # Message Broker Configuration
│   ├── kafka/                       # Kafka configurations
│   └── rabbitmq/                    # RabbitMQ configurations
├── monitoring-logging/              # Monitoring & Logging Tools
│   ├── elk-stack/                   # ELK Stack configurations
│   └── prometheus-grafana/          # Prometheus and Grafana configurations
└── tools/                           # Developer Tools & Scripts
    ├── code-formatter/              # Code formatting tools
    ├── linter/                      # Linting tools
    └── debugger/                    # Debugging tools
```

## Services Overview

### 1. **API Gateway**
   - Acts as a unified entry point for accessing the various services.
   - Handles routing, load balancing, and rate limiting.
   - Provides security features like authentication and authorization.
   - Technologies: Spring Cloud Gateway, Java, Docker, Helm.

### 2. **Authentication Service**
   - Manages user authentication and authorization.
   - Supports OAuth2, JWT, and OpenID Connect.
   - Integrates with identity providers.
   - Technologies: Java, Spring Security, Docker, Helm.

### 3. **User Service**
   - Manages user information and profiles.
   - Supports CRUD operations for user data.
   - Provides user-related reporting and analytics.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 4. **Invoice Service**
   - Handles invoice generation, processing, and tracking.
   - Integrates with payment and reporting services.
   - Supports multiple payment methods.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 5. **Payment Service**
   - Processes payments securely and efficiently.
   - Integrates with various payment gateways.
   - Supports credit card payments, bank transfers, etc.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 6. **Virtual Card Service**
   - Manages virtual card creation and operations.
   - Supports issuing, renewing, and revoking virtual cards.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 7. **Virtual Account Service**
   - Handles virtual account management.
   - Supports account creation, linking, and transactions.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 8. **Reporting Service**
   - Provides financial reports and dashboards.
   - Integrates with invoicing and payment services.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 9. **Notification Service**
   - Manages sending notifications (email, SMS, push).
   - Supports customizable notification templates.
   - Technologies: Java, Spring Boot, Docker, Helm.

### 10. **Config Service**
   - Central configuration management for all services.
   - Supports dynamic property updates and secret management.
   - Technologies: Spring Cloud Config, Docker, Helm.

### 11. **Discovery Service**
   - Facilitates service discovery and registration.
   - Technologies: Spring Cloud Eureka, Docker, Helm.

## Infrastructure

- **Infrastructure as Code (IaC)**: Uses Terraform to manage cloud infrastructure, including networking, databases, and Kubernetes clusters.
- **Kubernetes**: Deployed on Kubernetes for container orchestration, leveraging Helm charts for managing deployments.
- **Monitoring & Logging**: Uses the ELK stack and Prometheus with Grafana for centralized logging, monitoring, and alerting.

## Continuous Integration & Deployment

- **CI/CD Pipelines**: Configured with Azure Pipelines for automated build, testing, and deployment.
- **Scripts**: Custom scripts for build, deploy, and testing help streamline the development workflow.

## Getting Started

### Prerequisites

- Java 17 or above
- Docker and Docker Compose
- Kubernetes CLI (kubectl)
- Helm
- Terraform
- Azure DevOps account

### Running Locally

1. **Clone the Repository**
   ```bash
   git clone https://github.com/felixojiambo/FinPay.git
   cd FinPay
   ```

2. **Build Services**
   ```bash
   ./ci-cd/scripts/build.sh
   ```

3. **Deploy to Kubernetes**
   ```bash
   ./ci-cd/scripts/deploy.sh
   ```

4. **Access the API Gateway**
   - The API Gateway will be accessible at `http://localhost:8080`.

### Testing

- Run the test script:
   ```bash
   ./ci-cd/scripts/test.sh
   ```

## Documentation

- **System Architecture**: Detailed architecture diagrams and explanations can be found in `docs/architecture/`.
- **API Specifications**: OpenAPI and Postman collection files are located in `docs/api-specifications/`.
- **User Guides**: Usage manuals and guides for different user roles are available in `docs/user-guides/`.

## Contributing

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a Pull Request.

## License

This project is licensed under the MIT License.

