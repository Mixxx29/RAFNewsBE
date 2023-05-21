package rs.raf.rafnews.entities;

import java.util.List;

public class Tag {
    private Integer id;
    private String text;
    private List<News> news;

    public Tag() {

    }

    public Tag(Integer id, String text, List<News> news) {
        this.id = id;
        this.text = text;
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", news=" + news +
                '}';
    }
}
