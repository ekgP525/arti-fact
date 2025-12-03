package ajou.artifact.arti_fact.dto;

import lombok.*;

public class LikedDto {


    // 좋아요 추가/삭제 요청 시 사용
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String artId; // 어떤 작품을 좋아요 할 것인지
    }

    // 관심 목록 조회 시 사용
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long likedId;      // 좋아요 취소(삭제)를 위해 필요한 PK
        
        private String artId;     
        private String artName;    
        private String imageUrl;  
        private String artistName; 
        private String galleryName;
    }
}