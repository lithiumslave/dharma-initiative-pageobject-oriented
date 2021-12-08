package be.models.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetModel{

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private long id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("tags")
    private List<TagsItem> tags;

    @JsonProperty("status")
    private String status;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category{

        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        private int id;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagsItem{

        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        private int id;
    }
}