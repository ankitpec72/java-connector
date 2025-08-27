package io.github.ankitpec72.connector;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class ClientTest {
  @Test
  void buildsAndPings() {
    var client =
        Client.builder()
            .endpoint("https://example")
            .apiKey("sk_test")
            .timeout(Duration.ofSeconds(3))
            .build();
    assertEquals("pong", client.ping());
  }
}
