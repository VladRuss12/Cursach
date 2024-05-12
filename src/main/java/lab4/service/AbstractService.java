package lab4.service;

import java.util.List;
import lab4.entity.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {
	T read(Long id);
	List<T> readAll();
	void save(T entity);
	void delete(Long id);
	void edit(T entity);
}