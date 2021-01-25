package com.readitsoon.pabbas.model;

public class ModelClass {
   // String urlToImageIcon;
    String title;
    String url;
    String content;
    String dateAndTime;
    public ModelClass(String urlImage,String title,String dateAndTime,String url,String content)
    {
    // this.urlToImageIcon=urlImage;
     this.title=title;
     this.url=url;
     this.content=content;
     this.dateAndTime=dateAndTime;
    }
//    public String getUrlToImage()
//    {
//        return urlToImageIcon;
//    }
//    public void setUrlToImage(String urlToImage) {
//        this.urlToImageIcon = urlToImage;
//    }
    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }
}
