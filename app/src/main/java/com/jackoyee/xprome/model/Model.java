package com.jackoyee.xprome.model;

public class Model {
    private String name, realName, team, firstAppearance, createdBy, publisher, imageUrl, bio;

    public Model(String name, String realName, String team, String firstAppearance, String createdBy, String publisher, String imageUrl, String bio) {
        this.name = name;
        this.realName = realName;
        this.team = team;
        this.firstAppearance = firstAppearance;
        this.createdBy = createdBy;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getTeam() {
        return team;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBio() {
        return bio;
    }
}
