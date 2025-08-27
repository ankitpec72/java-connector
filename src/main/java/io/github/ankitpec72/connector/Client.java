package io.github.ankitpec72.connector;

import java.time.Duration;
import java.util.Objects;

/**
 * Client for connecting to the 3rd party services. Usage:
 *
 * <pre>
 * Client client = Client.builder()
 *     .apiKey("your-key")
 *     .endpoint("https://api.example.com")
 *     .timeout(Duration.ofSeconds(30))
 *     .build();
 * </pre>
 */
public final class Client {

  private final String endpoint;
  private final String apiKey;
  private final Duration timeout;

  private Client(Builder b) {
    this.endpoint = b.endpoint;
    this.apiKey = b.apiKey;
    this.timeout = b.timeout;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String ping() {
    return "pong";
  }

  public static final class Builder {
    private String endpoint;
    private String apiKey;
    private Duration timeout = Duration.ofSeconds(10);

    public Builder endpoint(String endpoint) {
      this.endpoint = Objects.requireNonNull(endpoint);
      return this;
    }

    public Builder apiKey(String apiKey) {
      this.apiKey = Objects.requireNonNull(apiKey);
      return this;
    }

    public Builder timeout(Duration timeout) {
      this.timeout = Objects.requireNonNull(timeout);
      return this;
    }

    public Client build() {
      if (endpoint == null || apiKey == null)
        throw new IllegalStateException("endpoint and apiKey required");
      return new Client(this);
    }
  }
}
