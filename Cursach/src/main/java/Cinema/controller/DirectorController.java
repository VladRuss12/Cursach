package Cinema.controller;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController extends AbstractController<Director> {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(@Qualifier("directorServiceImpl") DirectorService directorService) {

        this.directorService = directorService;
    }

    @Override
    public DirectorService getService() {
        return this.directorService;
    }
}

//