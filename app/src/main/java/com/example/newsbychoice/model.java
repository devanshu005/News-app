package com.example.newsbychoice;

public class model {
    String image,source,title,description,link;

    public model(String image, String source, String title, String description,String link) {
        this.image = image;
        this.source = source;
        this.title = title;
        this.description = description;
        this.link=link;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

}

