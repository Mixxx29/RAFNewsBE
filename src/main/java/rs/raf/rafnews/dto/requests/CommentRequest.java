package rs.raf.rafnews.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
@NoArgsConstructor
public class CommentRequest {
    private String content;
    private Date datetime;
    private int authorId;
    private int newsId;
}
