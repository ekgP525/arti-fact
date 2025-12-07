package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.dto.LikedDto;
import ajou.artifact.arti_fact.entity.Art;
import ajou.artifact.arti_fact.entity.Liked;
import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.repository.ArtRepository;
import ajou.artifact.arti_fact.repository.LikedRepository;
import ajou.artifact.arti_fact.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikedService {

    private final LikedRepository likedRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;

    public List<LikedDto.Response> getLikedListByUser(@NonNull Long userId) {
        // existsById 대신 직접 조회로 최적화 (N+1 문제 해결된 쿼리 사용)
        List<Liked> likedList = likedRepository.findByUser_UserId(userId);
        if (likedList.isEmpty() && !userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        return likedList.stream()
                .map(LikedDto.Response::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public LikedDto.ToggleResponse toggleLikeStatus(@NonNull LikedDto.Create request) {
        // 존재 여부만 확인 (최적화)
        Optional<Liked> existingLiked = likedRepository.findByUser_UserIdAndArt_ArtId(request.getUserId(), request.getArtId());

        if (existingLiked.isPresent()) {
            // '좋아요'가 이미 존재하면 삭제 (직접 쿼리 사용으로 성능 향상)
            likedRepository.deleteByUser_UserIdAndArt_ArtId(request.getUserId(), request.getArtId());
            return new LikedDto.ToggleResponse(false, null); // isLiked: false
        } else {
            // '좋아요'가 없으면 새로 생성
            // 프록시 객체 사용으로 불필요한 조회 최소화
            User user = userRepository.getReferenceById(request.getUserId());
            Art art = artRepository.getReferenceById(request.getArtId());
            
            Liked newLiked = new Liked(user, art);
            Liked savedLiked = likedRepository.save(newLiked);
            return new LikedDto.ToggleResponse(true, LikedDto.Response.from(savedLiked)); // isLiked: true
        }
    }

    @Transactional
    public void removeLikedItem(@NonNull Long likedId) {
        if (!likedRepository.existsById(likedId)) {
            throw new EntityNotFoundException("Liked item not found with id: " + likedId);
        }
        likedRepository.deleteById(likedId);
    }
}
