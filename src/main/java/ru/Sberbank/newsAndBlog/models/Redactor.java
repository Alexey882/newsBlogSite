package ru.Sberbank.newsAndBlog.models;
import javax.persistence.*;
import java.util.Collection;

@Entity
public class Redactor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    String name , surname , mail;
    @OneToMany(mappedBy = "redactor" , fetch = FetchType.EAGER)
    private Collection<News> news;
    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
