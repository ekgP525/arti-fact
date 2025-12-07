package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.dto.ArtDto;
import ajou.artifact.arti_fact.entity.Art;
import ajou.artifact.arti_fact.service.ArtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/arts")
public class ArtController {

    private final ArtService artService;

    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArtDto.Response>> searchArts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String theme,
            @RequestParam(required = false) String artistName,
            @RequestParam(required = false) String galleryName
    ) {
        List<Art> arts = artService.searchArts(name, genre, theme, artistName, galleryName);
        
        List<ArtDto.Response> artResponses = arts.stream()
                // 정렬 로직 적용
                .sorted(Comparator.comparing(Art::getAge, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(ArtDto.Response::from)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(artResponses);
    }

    @GetMapping
    public ResponseEntity<List<ArtDto.Response>> findAllArts() {
        List<Art> arts = artService.findAllArts();
        
        List<ArtDto.Response> artResponses = arts.stream()
                // 정렬 로직 적용: 년도(Age) 오름차순 (옛날 -> 최신), Null은 맨 뒤로
                .sorted(Comparator.comparing(Art::getAge, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(ArtDto.Response::from)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(artResponses);
    }

    @GetMapping("/{artId}")
    public ResponseEntity<ArtDto.Response> findArtById(@PathVariable String artId) {
        Art art = artService.findArtById(artId);
        return ResponseEntity.ok(ArtDto.Response.from(art));
    }
}