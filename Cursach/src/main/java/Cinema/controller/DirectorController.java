package Cinema.controller;

import Cinema.entity.Director;
import Cinema.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController extends PersonController<Director> {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/movie/{title}")
    public ResponseEntity<List<Director>> getDirectorsByMovieTitle(@PathVariable String title) {
        List<Director> directors = directorService.findByMovieTitle(title);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @Override
    public DirectorService getService() {
        return this.directorService;
    }
}
    //Напиши теперь DirectorController. Напиши код полностью и с маппингом