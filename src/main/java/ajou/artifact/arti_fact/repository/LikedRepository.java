package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Liked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {
    
    // N+1 문제 해결: User와 Art를 fetch join으로 가져오기
    @EntityGraph(attributePaths = {"user", "art.artist", "art.gallery"})
    @Query("SELECT l FROM Liked l WHERE l.user.userId = :userId")
    List<Liked> findByUser_UserId(@Param("userId") Long userId);
    
    @EntityGraph(attributePaths = {"user", "art.artist", "art.gallery"})
    @Query("SELECT l FROM Liked l WHERE l.user.userId = :userId AND l.art.artId = :artId")
    Optional<Liked> findByUser_UserIdAndArt_ArtId(@Param("userId") Long userId, @Param("artId") String artId);
    
    // 직접 삭제 쿼리로 성능 최적화
    @Modifying
    @Transactional
    @Query("DELETE FROM Liked l WHERE l.user.userId = :userId AND l.art.artId = :artId")
    void deleteByUser_UserIdAndArt_ArtId(@Param("userId") Long userId, @Param("artId") String artId);
}
