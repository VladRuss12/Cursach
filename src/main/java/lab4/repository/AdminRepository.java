package lab4.repository;

import lab4.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends PersonRepository<Admin, Long> {
}