package com.example.soadashboard;

public class news_data {
    private int image;
    private String judul;
    private String konten;

    public news_data(int image, String judul, String konten) {
        this.image = image;
        this.judul = judul;
        this.konten = konten;
    }

    public int getImage() {
        return image;
    }

    public String getJudul() {
        return judul;
    }

    public String getKonten() {
        return konten;
    }
}
