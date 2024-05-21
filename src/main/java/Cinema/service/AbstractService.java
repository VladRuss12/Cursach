package Cinema.service;

import Cinema.entity.AbstractEntity;

import java.util.List;

public interface AbstractService<T extends AbstractEntity> {
    T read(Long id);

    List<T> read();

    void save(T entity);

    void delete(Long id);

    void edit(T entity);
}