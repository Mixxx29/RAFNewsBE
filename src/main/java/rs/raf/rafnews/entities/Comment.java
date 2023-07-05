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

@Entity("comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @ID
    private Integer id;
    @ManyToOne
    @Column("author_id")
    private User author;
    private String content;
    private Date datetime;
    @ManyToOne
    @Column("news_id")
    private News news;
}
