package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.entity.Art;
import ajou.artifact.arti_fact.repository.ArtRepository;
import ajou.artifact.arti_fact.repository.ArtSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArtService {

    private final ArtRepository artRepository;

    public ArtService(ArtRepository artRepository) {
        this.artRepository = artRepository;
    }

    public List<Art> searchArts(String name, String genre, String theme, String artistName, String galleryName) {
        // Specification에 fetch join이 포함되어 있어 N+1 문제 해결됨
        return artRepository.findAll(ArtSpecification.search(name, genre, theme, artistName, galleryName));
    }

    public Page<Art> findAllArts(@NonNull Pageable pageable) {
        return artRepository.findAll(pageable);
    }

    public List<Art> findAllArts() {
        // N+1 문제 해결: fetch join 사용
        return artRepository.findAllWithArtistAndGalleryOrdered();
    }

    public Art findArtById(String artId) {
        // N+1 문제 해결: fetch join 사용
        return artRepository.findByIdWithArtistAndGallery(artId)
                .orElseThrow(() -> new IllegalArgumentException("Art not found with ID: " + artId));
    }
}
