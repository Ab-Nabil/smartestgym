package com.example.afinal.Admin;

import android.net.Uri;

public class UsersAdmin {
    private String username;
    private int image;
//    private DateFormat dateFormat;

    public UsersAdmin() {

    }

    public UsersAdmin(String username, int image) {
        this.username = username;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public int getImage() {
        return image;
    }
}
