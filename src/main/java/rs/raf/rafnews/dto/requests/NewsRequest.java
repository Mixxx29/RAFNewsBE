package rs.raf.rafnews.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
@NoArgsConstructor
public class NewsRequest {
    private int id;
    private String title;
    private String content;
    private Date datetime;
    private int visits;
    private int authorId;
    private int categoryId;
}
