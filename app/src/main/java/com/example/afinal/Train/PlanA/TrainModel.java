package com.example.afinal.Train.PlanA;

public class TrainModel {
    String  topText,bottomText;
    int img;
    int video;

    public int getVideo() {
        return video;
    }

    public TrainModel( int video) {
        this.video = video;
    }

    public TrainModel(String topText, String bottomText, int img) {
        this.topText = topText;
        this.bottomText = bottomText;
        this.img = img;
    }

    public String getTopText() {
        return topText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public int getImg() {
        return img;
    }
}
