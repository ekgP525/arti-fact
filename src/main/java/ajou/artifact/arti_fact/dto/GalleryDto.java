package ajou.artifact.arti_fact.dto;

import lombok.*;

public class GalleryDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GalleryResponse {
        private String galleryId;
        private String name;
        private String address;
        private String openTime;
        private String closedTime;
        private Integer fee;
        private String phone;
    }
}
