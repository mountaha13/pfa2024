package ma.xproce.carte.dao.repositories;

import ma.xproce.carte.dao.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
