package rs.raf.rafnews.entities;

import rs.raf.rafnews.annotations.Column;

import java.util.List;
import java.util.Objects;

public class News {

    private String title;
    private String content;
    private String datetime;
    private int numberOfVisits;
    @Column("author_id")
    private User author;
    private List<Comment> comments;
    private List<Tag> tags;
    private Category category;

    public News() {

    }

    public News(
            String title,
            String content,
            String datetime,
            int numberOfVisits,
            User author,
            List<Comment> comments,
            List<Tag> tags,
            Category category
    ) {
        this();
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.numberOfVisits = numberOfVisits;
        this.author = author;
        this.comments = comments;
        this.tags = tags;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return numberOfVisits == news.numberOfVisits
                && Objects.equals(title, news.title)
                && Objects.equals(content, news.content)
                && Objects.equals(datetime, news.datetime)
                && Objects.equals(author, news.author)
                && Objects.equals(comments, news.comments)
                && Objects.equals(tags, news.tags)
                && Objects.equals(category, news.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, datetime, numberOfVisits, author, comments, tags, category);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", datetime='" + datetime + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                ", author=" + author +
                ", comments=" + comments +
                ", tags=" + tags +
                ", category=" + category +
                '}';
    }
}
