package rs.raf.rafnews.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.raf.rafnews.annotations.Entity;
import rs.raf.rafnews.annotations.ID;

@Entity("category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @ID
    private Integer id;
    private String name;
    private String description;
}
