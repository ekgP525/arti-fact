package ajou.artifact.arti_fact.reposiroty;

import ajou.artifact.arti_fact.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}