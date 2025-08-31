# Java Connector

[![Maven Central](https://img.shields.io/maven-central/v/io.github.ankitpec72/java-connector.svg)](https://search.maven.org/artifact/io.github.ankitpec72/java-connector)
[![Build Status](https://github.com/ankitpec72/java-connector/workflows/CI%20-%20Build%20&%20Test/badge.svg)](https://github.com/ankitpec72/java-connector/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ankitpec72_java-connector&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ankitpec72_java-connector)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ankitpec72_java-connector&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ankitpec72_java-connector)

A lightweight, dependency-minimal Java library for connecting to third-party services with a focus on simplicity, reliability, and developer experience.

## Features

- **Zero heavy dependencies** - Built with minimal external dependencies
- **Well documented** - Comprehensive JavaDoc and examples
- **Timeout handling** - Configurable request timeouts
- **Thoroughly tested** - High test coverage with integration tests

## Installation

### Maven

Add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.ankitpec72</groupId>
    <artifactId>java-connector</artifactId>
    <version>0.1.0-rc1</version>
</dependency>
```

### Gradle

Add this dependency to your `build.gradle`:

```gradle
implementation 'io.github.ankitpec72:java-connector:0.1.0-rc1'
```

## Quick Start

```java
import io.github.ankitpec72.connector.Client;
import java.time.Duration;

public class Example {
    public static void main(String[] args) {
        // Create a client instance
        Client client = Client.builder()
            .endpoint("https://api.example.com")
            .apiKey("your-api-key-here")
            .timeout(Duration.ofSeconds(30))
            .build();
        
        // Test the connection
        String response = client.ping();
        System.out.println("Response: " + response); // "pong"
    }
}
```

## Usage Examples

### Basic Configuration

```java
Client client = Client.builder()
    .endpoint("https://api.service.com")
    .apiKey("sk_test_...")
    .timeout(Duration.ofSeconds(30))
    .build();
```

### Advanced Configuration

```java
Client client = Client.builder()
    .endpoint("https://api.service.com")
    .apiKey("sk_live_...")
    .timeout(Duration.ofMinutes(2))
    .build();

// Use the client for operations
String result = client.ping();
```

## Authentication

The library supports API key authentication. Set your API key when building the client:

```java
Client client = Client.builder()
    .apiKey("your-secret-api-key")
    .endpoint("https://api.yourservice.com")
    .build();
```

**Security Note**: Never hardcode API keys in your source code. Use environment variables or configuration files:

```java
String apiKey = System.getenv("SERVICE_API_KEY");
Client client = Client.builder()
    .apiKey(apiKey)
    .endpoint("https://api.yourservice.com")
    .build();
```

## Configuration Options

| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| `endpoint` | String | ✅ | - | Base URL for the API service |
| `apiKey` | String | ✅ | - | Authentication key for the service |
| `timeout` | Duration | ❌ | 10 seconds | Request timeout duration |

## Error Handling

The library provides specific exceptions for different error scenarios:

```java
try {
    String result = client.ping();
} catch (ConnectorException e) {
    // Handle connector-specific errors
    System.err.println("Connection failed: " + e.getMessage());
}
```

## Thread Safety

All client instances are **thread-safe** and can be shared across multiple threads. For best performance, reuse client instances rather than creating new ones for each request.

```java
// Good - reuse the client
Client client = Client.builder()...build();

// Use the same client instance across your application
String result1 = client.ping();
String result2 = client.ping();
```

## Requirements

- **Java 17+** - This library requires Java 17 or higher
- **Maven 3.6+** - For building from source

## Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/ankitpec72/java-connector.git
cd java-connector

# Build the project
mvn clean compile

# Run tests
mvn test

# Run integration tests
mvn verify

# Build JAR
mvn package
```

### Code Formatting

This project uses **Spotless** with Google Java Format for consistent code styling:

```bash
# Check code formatting
mvn spotless:check

# Apply code formatting
mvn spotless:apply
```

The CI pipeline automatically formats code on push to the development branch.

### Running Tests

```bash
# Run unit tests only
mvn test

# Run all tests including integration tests
mvn verify

# Run tests with coverage report
mvn clean verify
# View coverage report at target/site/jacoco/index.html
```

### Development Workflow

This project follows **GitFlow**:

1. **Feature development**: Create feature branches from `development`
2. **Pull requests**: Submit PRs to `development` branch
3. **Release process**: Merge `development` to `master` triggers automatic release
4. **Hotfixes**: Create hotfix branches from `master` when needed

### Code Quality Standards

- **Test coverage**: Aim for >80% line coverage (tracked by SonarCloud)
- **Documentation**: All public APIs must have JavaDoc
- **Code formatting**: Enforced via Spotless with Google Java Format
- **Security**: Comprehensive analysis via SonarCloud (vulnerabilities + security hotspots)
- **Quality gates**: All PRs must pass SonarCloud quality gate

## CI/CD Pipeline

The project includes comprehensive GitHub Actions workflows:

- **Continuous Integration** (`ci.yml`): Runs on all PRs and pushes
  - Multi-JDK testing
  - SonarCloud code quality & security analysis
  - Spotless code formatting checks
  - Build verification

- **Development Workflow** (`development.yml`):
  - Automatic code formatting
  - Release readiness checks

- **Auto Release** (`auto-release.yml`):
  - Semantic versioning based on PR labels/titles
  - Automatic Maven Central deployment
  - GitHub releases with generated notes

### Version Management

Versions are automatically managed using semantic versioning:

- **Patch** (`x.y.Z`): Bug fixes, documentation updates
- **Minor** (`x.Y.0`): New features, backwards compatible
- **Major** (`X.0.0`): Breaking changes

Use PR labels to control version bumps:
- `patch`, `bugfix`, `fix` → Patch version bump
- `minor`, `feature` → Minor version bump
- `major`, `breaking` → Major version bump

## Contributing

1. **Fork** the repository
2. **Create** a feature branch from `development`
3. **Make** your changes with tests
4. **Format** code using `mvn spotless:apply`
5. **Submit** a pull request to `development`

### Commit Message Format

Follow conventional commits for automatic release notes:

```
feat: add new authentication method
fix: resolve timeout handling bug
docs: update API documentation
test: add integration tests for webhooks
```

## Support

- **Issues**: [GitHub Issues](https://github.com/ankitpec72/java-connector/issues)
- **Discussions**: [GitHub Discussions](https://github.com/ankitpec72/java-connector/discussions)
- **Documentation**: [Wiki](https://github.com/ankitpec72/java-connector/wiki)
- **Code Quality**: [SonarCloud Dashboard](https://sonarcloud.io/summary/new_code?id=ankitpec72_java-connector)

## Important Notes

### Group ID Structure

This library uses the group ID `io.github.ankitpec72` following **Maven Central's requirements for GitHub-hosted projects**:

- **Format**: `io.github.{username}` for GitHub users
- **Purpose**: Ensures global uniqueness across Maven repositories
- **Verification**: Maven Central automatically verifies ownership through GitHub
- **Standard**: Industry-standard pattern used by thousands of open source libraries

**Alternative approaches** (for future consideration):
- **Custom domain**: If you own a domain like `xyz.dev`, you could use `dev.xyz.connector`
- **Organization**: For companies, use reverse domain like `com.company.product`

### Quality Metrics

This project uses **SonarCloud** for continuous code quality monitoring:

- **Coverage Target**: >80% line coverage
- **Quality Gate**: Must pass on all PRs
- **Security**: Automatic detection of vulnerabilities and security hotspots
- **Code Smells**: Tracked and resolved regularly
- **Maintainability**: Technical debt kept under control

**Local SonarCloud Analysis:**
```bash
# Set your SonarCloud token
export SONAR_TOKEN="your-sonarcloud-token"

# Run analysis locally
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=ankitpec72_java-connector \
  -Dsonar.organization=ankitpec72
```

View results at: [SonarCloud Dashboard](https://sonarcloud.io/summary/new_code?id=ankitpec72_java-connector)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Note**: This library is currently in early development. APIs may change before v1.0.0 release.