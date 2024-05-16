package Cinema.controller;

import Cinema.entity.Actor;
import Cinema.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController extends PersonController<Actor> {

    private final ActorService actorService;

    @Autowired
    public ActorController(@Qualifier("actorServiceImpl") ActorService actorService) {
        super(actorService);
        this.actorService = actorService;
    }

    @GetMapping("/movie/{title}")
    public ResponseEntity<List<Actor>> getActorsByMovieTitle(@PathVariable String title) {
        List<Actor> actors = actorService.findByMovieTitle(title);
        if (actors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @Override
    public ActorService getService() {
        return this.actorService;
    }
}
