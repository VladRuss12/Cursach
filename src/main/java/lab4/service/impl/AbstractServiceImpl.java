package lab4.service.impl;

import lab4.entity.AbstractEntity;
import lab4.repository.AbstractRepository;
import lab4.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public abstract class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {
    private JpaRepository<T, Long> repository;

    @Autowired
    public AbstractServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public T read(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    public List<T> readAll() {
        return repository.findAll();
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
