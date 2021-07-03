package ru.Sberbank.newsAndBlog.models;
import javax.persistence.*;
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String time , fullText ;
    private double rating;
    private String category;
    @ManyToOne(optional = true , cascade = CascadeType.ALL)
    @JoinColumn(name="redactor_id")
    private Redactor redactor;
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getFullText() {
        return fullText;
    }
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }
    public Redactor getRedactor() {
        return redactor;
    }
    public void setRedactor(Redactor redactor) {
        this.redactor = redactor;
    }
}
