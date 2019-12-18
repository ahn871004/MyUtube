package kr.co.ajsoft.myutube.Model;

public class Item {

    String title;
    String id;
    String imgUrl;
    String date;
    String views;
    String publisher;

    public Item() {

    }

    public Item(String title, String id, String imgUrl, String date, String views, String publisher) {
        this.title = title;
        this.id = id;
        this.imgUrl = imgUrl;
        this.date = date;
        this.views = views;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
