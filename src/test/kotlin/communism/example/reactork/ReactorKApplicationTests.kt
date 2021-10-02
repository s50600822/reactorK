package communism.example.reactork

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration.ofSeconds


@SpringBootTest
class ReactorKApplicationTests {
    val client = WebClient.builder().baseUrl("http://localhost:8080").build()

    @Test
    fun contextLoads() {
    }

    @Test
    fun test() {
        StepVerifier.create(getWithDelay(10))
            .expectNext("ok")
            .expectComplete()
            .verify(ofSeconds(11L))
    }

    @Test
    fun testConcatFlux() {
        StepVerifier.create(
            Flux.concat(
                getWithDelay(1),
                getWithDelay(2),
                getWithDelay(3),
                getWithDelay(4),
                getWithDelay(5),
                getWithDelay(6),
                getWithDelay(7),
                getWithDelay(8),
                getWithDelay(9),
                getWithDelay(10)
            )
        ).expectNextCount(10).expectComplete().verify(ofSeconds(60L))
    }

    @Test
    fun testMergeFlux() {
        StepVerifier.create(
            Flux.merge(
                getWithDelay(1),
                getWithDelay(2),
                getWithDelay(3),
                getWithDelay(4),
                getWithDelay(5),
                getWithDelay(6),
                getWithDelay(7),
                getWithDelay(8),
                getWithDelay(9),
                getWithDelay(10)
            )
        ).expectNextCount(10).expectComplete().verify(ofSeconds(11L))
    }

    fun getWithDelay(delaySec: Long): Flux<String> = client.get().uri("/delay/$delaySec")
        .retrieve()
        .bodyToFlux(String::class.java)
}
