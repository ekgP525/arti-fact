package ajou.artifact.arti_fact.dto;

import lombok.*;

public class ArtistDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ArtistResponse {
        private String artistId;
        private String name;
        private Integer birthDate;
        private Integer deadDate;   // 생존 작가일 경우 null 가능
        private String nationality;
        private String theme;
        private String info;
    }
}