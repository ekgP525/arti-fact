package ajou.artifact.arti_fact.reposiroty;

import ajou.artifact.arti_fact.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}