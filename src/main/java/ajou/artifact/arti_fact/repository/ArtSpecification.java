package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Art;
import ajou.artifact.arti_fact.entity.Artist;
import ajou.artifact.arti_fact.entity.Gallery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ArtSpecification {

    public static Specification<Art> search(String name, String genre, String theme, String artistName, String galleryName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // fetch join을 사용하여 N+1 문제 해결
            boolean needsArtistJoin = artistName != null && !artistName.isEmpty();
            boolean needsGalleryJoin = galleryName != null && !galleryName.isEmpty();
            
            if (needsArtistJoin) {
                root.fetch("artist");
            }
            if (needsGalleryJoin) {
                root.fetch("gallery");
            }
            
            // DISTINCT를 사용하여 중복 제거 (fetch join으로 인한 중복 방지)
            if ((needsArtistJoin || needsGalleryJoin) && query != null) {
                query.distinct(true);
            }

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (genre != null && !genre.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("genre"), "%" + genre + "%"));
            }
            if (theme != null && !theme.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("theme"), "%" + theme + "%"));
            }
            if (needsArtistJoin) {
                Join<Art, Artist> artistJoin = root.join("artist");
                predicates.add(criteriaBuilder.like(artistJoin.get("name"), "%" + artistName + "%"));
            }
            if (needsGalleryJoin) {
                Join<Art, Gallery> galleryJoin = root.join("gallery");
                predicates.add(criteriaBuilder.like(galleryJoin.get("name"), "%" + galleryName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

