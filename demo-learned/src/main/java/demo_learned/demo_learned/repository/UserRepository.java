package demo_learned.demo_learned.repository;

import demo_learned.demo_learned.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    boolean existsByUsername(String s);


    Optional<User> findByUsername(String s);
}
