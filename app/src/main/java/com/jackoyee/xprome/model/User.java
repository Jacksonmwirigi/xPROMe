package com.jackoyee.xprome.model;


import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("USER_ID")
    private int USER_ID;
    @SerializedName("USERNAME")
    private String USERNAME;
    @SerializedName("NAME")
    private String NAME;
    @SerializedName("CURRENT_SESSION_KEY")
    private String CURRENT_SESSION_KEY;
    @SerializedName("PHONE_NUMBER")
    private String PHONE_NUMBER;
    @SerializedName("ROLE")
    private String ROLE;
    @SerializedName("EMAIL_ADDRESS")
    private String EMAIL_ADDRESS;
    @SerializedName("DESIGNATED_REGION")
    private String DESIGNATED_REGION;
    @SerializedName("ticket")
    private String ticket;
    @SerializedName("client_loc_lat")
    private  String  client_loc_lat;
    @SerializedName("client_loc_long")
    private  String client_loc_long;
    @SerializedName("comment")
    private  String comment;
    @SerializedName("status")
    private  String status;

    public User() {

    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getCURRENT_SESSION_KEY() {
        return CURRENT_SESSION_KEY;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public String getROLE() {
        return ROLE;
    }

    public String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public String getDESIGNATED_REGION() {
        return DESIGNATED_REGION;
    }

    public String getTicket() {
        return ticket;
    }

    public String getClient_loc_lat() {
        return client_loc_lat;
    }

    public String getClient_loc_long() {
        return client_loc_long;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }
}
