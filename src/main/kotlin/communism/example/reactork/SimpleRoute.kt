package communism.example.reactork

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.ALL
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.time.Duration.ofSeconds

@Configuration
class SimpleRoute {

    @Bean
    fun route(): RouterFunction<ServerResponse> = router {
        accept(ALL).nest {
            GET("/delay/{delaySec}") {
                ServerResponse.ok()
                    .contentType(TEXT_PLAIN)
                    .body(delay(it), String::class.java)
            }
        }
    }

    private fun delay(req: ServerRequest) =
        Mono.delay(ofSeconds(req.pathVariable("delaySec").toLong()))
            .map { "ok" }
}
