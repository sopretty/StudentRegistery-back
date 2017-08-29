package fr.univlorraine.boundary;

import fr.univlorraine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResource extends JpaRepository<User, Long>{
}
