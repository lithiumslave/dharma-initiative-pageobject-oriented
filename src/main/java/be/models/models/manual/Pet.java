package be.models.models.manual;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    long id;
    String name;
    String status;
    String[] photoUrls;
    Category category;
    List<Tag> tags;

}