package communism.example.reactork

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorKApplication

fun main(args: Array<String>) {
    runApplication<ReactorKApplication>(*args)
}

//@GetMapping(path = ["/numbers"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
//@ResponseBody
//fun getNumbers() = Flux.range(1, 100)
