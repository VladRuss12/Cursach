package Cinema.repository;

import Cinema.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends PersonRepository<Admin, Long> {
}