package org.castlefight.castlefight;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("game")
public class GameController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:8080")
    public Flux<GameEvent> stockTransactionEvents(){
        Flux<GameEvent> stockTransactionFlux = Flux.fromStream(Stream.generate(() -> new GameEvent(0L, "event")));
        return Flux.zip(Flux.interval(Duration.ofSeconds((1))), stockTransactionFlux).map( flux -> flux.getT2());
    }
}
