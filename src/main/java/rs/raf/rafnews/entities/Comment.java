package rs.raf.rafnews.entities;

public class Comment {

    private Integer id;
    private String authorName;
    private String content;
    private String datetime;
    private News news;

    public Comment() {

    }

    public Comment(
            Integer id,
            String authorName,
            String content,
            String datetime,
            News news
    ) {
        this();
        this.id = id;
        this.authorName = authorName;
        this.content = content;
        this.datetime = datetime;
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", content='" + content + '\'' +
                ", datetime='" + datetime + '\'' +
                ", news=" + news +
                '}';
    }
}
