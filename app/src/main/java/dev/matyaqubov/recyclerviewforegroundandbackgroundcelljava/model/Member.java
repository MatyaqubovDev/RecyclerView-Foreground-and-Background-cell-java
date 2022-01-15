package dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.model;

public class Member {
    private int image;
    private String name;
    private String phone;

    public Member(int image, String name, String phone) {
        this.image = image;
        this.name = name;
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
