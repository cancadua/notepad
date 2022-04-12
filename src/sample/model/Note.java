package sample.model;

import java.io.Serializable;

public class Note implements Serializable {

    String id;
    String user;
    String title;
    String content;
    String noteDate;

    public Note() {
    }

    public Note(String user_, String title_, String content_, String noteDate_, String id_) {
        user = user_;
        title = title_;
        content = content_;
        noteDate = noteDate_;
        id = id_;
    }

    public Note(String user_, String title_, String content_, String noteDate_) {
        user = user_;
        title = title_;
        content = content_;
        noteDate = noteDate_;
    }

    public String getUser() {
        return user;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getNoteDate(){
        return noteDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id_) {
        id = id_;
    }

    @Override
    public String toString() {
        return "{" +
                "user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noteDate='" + noteDate + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
