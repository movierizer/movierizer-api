package fr.movierizer.movierizerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.movierizer.movierizerapi.model.User;

/*For each method (create or provided in JpaRepository) it will provide a query  */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}