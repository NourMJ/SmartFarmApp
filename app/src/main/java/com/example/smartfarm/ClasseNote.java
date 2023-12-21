package com.example.smartfarm;

public class ClasseNote {
    private int id;
    private String content;

    public ClasseNote( String content) {
        this.id = id;
        this.content = content;
    }

    public ClasseNote()
    {

    }

    public   int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ClasseNote{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

}
