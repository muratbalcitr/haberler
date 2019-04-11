package com.murat.haberler.model;

public class News {
    private String description, name_url, author, title, url, urlToImage, yayin_tarihi, haber_content;
    private int status, total_result;

    public News(String title, String description, String urlToImage) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
    }

    public News(String description, String name_url, String author, String title, String url, String urlToImage, String yayin_tarihi, String haber_content) {
        this.description = description;
        this.name_url = name_url;
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.yayin_tarihi = yayin_tarihi;
        this.haber_content = haber_content;
    }

    public String getName_url() {
        return name_url;
    }

    public void setName_url(String name_url) {
        this.name_url = name_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getYayin_tarihi() {
        return yayin_tarihi;
    }

    public void setYayin_tarihi(String yayin_tarihi) {
        this.yayin_tarihi = yayin_tarihi;
    }

    public String getHaber_content() {
        return haber_content;
    }

    public void setHaber_content(String haber_content) {
        this.haber_content = haber_content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_result() {
        return total_result;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }
}
