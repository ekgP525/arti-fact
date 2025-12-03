package ajou.artifact.arti_fact.dto;

import lombok.*;

public class ArtDto {

    // 미술품 상세/목록 응답 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ArtResponse {
        private String artId;
        private String name;      
        private String imageUrl;  
        
        private String artistId;
        private String artistName;
        
        private String galleryId;
        private String galleryName;

        private Boolean display;
        private String genre;
        private String theme;
        private Integer age;       
    }
    
}