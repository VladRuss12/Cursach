package Cinema.service;

import java.util.List;
import Cinema.entity.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {
	T read(Long id);
	List<T> readAll();
	void save(T entity);
	void delete(Long id);
	void edit(T entity);
}