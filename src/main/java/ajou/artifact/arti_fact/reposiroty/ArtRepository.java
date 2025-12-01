package ajou.artifact.arti_fact.reposiroty;

import ajou.artifact.arti_fact.entity.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtRepository extends JpaRepository<Art, Long>, JpaSpecificationExecutor<Art> {
}
