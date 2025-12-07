package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Art;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtRepository extends JpaRepository<Art, String>, JpaSpecificationExecutor<Art> {
    
    // N+1 문제 해결: Artist와 Gallery를 fetch join으로 가져오기
    @EntityGraph(attributePaths = {"artist", "gallery"})
    @Query("SELECT a FROM Art a")
    List<Art> findAllWithArtistAndGallery();
    
    @EntityGraph(attributePaths = {"artist", "gallery"})
    @Query("SELECT a FROM Art a WHERE a.artId = :artId")
    Optional<Art> findByIdWithArtistAndGallery(@Param("artId") String artId);
    
    // 페이징 처리와 함께 fetch join
    @EntityGraph(attributePaths = {"artist", "gallery"})
    @Query("SELECT a FROM Art a ORDER BY a.age ASC NULLS LAST")
    List<Art> findAllWithArtistAndGalleryOrdered();
}

