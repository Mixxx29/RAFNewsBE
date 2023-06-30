package rs.raf.rafnews.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
@NoArgsConstructor
public class NewsRequest {
    private Integer id;
    private String title;
    private String content;
    private Date datetime;
    private int numberOfVisits;
    private Integer authorId;
    private Integer categoryId;
}
