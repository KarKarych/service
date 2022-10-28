package example.micronaut;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import reactor.core.publisher.Mono;

@KafkaClient
public interface AnalyticsClient {

  @Topic("analytics")
    // <1>
  Mono<Book> updateAnalytics(Book book); // <2>
}
