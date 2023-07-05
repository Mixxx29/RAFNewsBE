package rs.raf.rafnews.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.raf.rafnews.annotations.Column;
import rs.raf.rafnews.annotations.Entity;
import rs.raf.rafnews.annotations.ID;
import rs.raf.rafnews.annotations.ManyToOne;

import java.sql.Date;


@Entity("news")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @ID
    private Integer id;
    private String title;
    private String content;
    private Date datetime;
    private int visits;
    @ManyToOne
    @Column("author_id")
    private User author;
    @ManyToOne
    @Column("category_id")
    private Category category;
    /*@ManyToMany
    private List<Tag> tags;*/

}
