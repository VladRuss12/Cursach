package lab4.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lab4.entity.AbstractEntity;
import lab4.service.AbstractService;

public abstract class AbstractController<T extends AbstractEntity> {

	protected HttpHeaders headers;

	@PostConstruct
	private void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@GetMapping
	public ResponseEntity<List<T>> get() {
		List<T> entities = getService().readAll();
		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, headers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable long id) {
		T entity = getService().read(id);
		if (entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, headers, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> put(@RequestBody T entity) {
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping
	public ResponseEntity<String> post(@RequestBody T entity) {
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		getService().delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	public abstract AbstractService<T> getService();
}