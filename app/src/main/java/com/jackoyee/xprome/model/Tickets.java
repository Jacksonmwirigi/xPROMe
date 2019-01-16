package com.jackoyee.xprome.model;


import com.google.gson.annotations.SerializedName;

public class Tickets {
    @SerializedName("DISPATCH_ID")
    private  String  DISPATCH_ID;
    @SerializedName("TICKET")
    private  String TICKET;
    @SerializedName("REQUEST_TYPE")
    private  String REQUEST_TYPE;
    @SerializedName("ASSIGNEE")
    private  String ASSIGNEE;
    @SerializedName("ASSIGNMENT_DATE")
    private  String  ASSIGNMENT_DATE;
    @SerializedName("ASSIGNER")
    private  String ASSIGNER;
    @SerializedName("LAST_UPDATE")
    private  String LAST_UPDATE;
    @SerializedName("STATUS")
    private  String STATUS;
    @SerializedName("COMMENTS")
    private  String  COMMENTS;
    @SerializedName("SCHEDULED_TIME_SLOT")
    private  String SCHEDULED_TIME_SLOT;
    @SerializedName("CLIENT_NAME")
    private  String CLIENT_NAME;
    @SerializedName("CLIENT_RESIDENCE")
    private  String CLIENT_RESIDENCE;
    @SerializedName("CLIENT_PHONE")
    private  String  CLIENT_PHONE;
    @SerializedName("SCHEDULED_DATE")
    private  String SCHEDULED_DATE;

    public Tickets( String DISPATCH_ID, String TICKET, String REQUEST_TYPE, String ASSIGNEE, String ASSIGNMENT_DATE, String ASSIGNER, String LAST_UPDATE, String STATUS, String COMMENTS, String SCHEDULED_TIME_SLOT, String CLIENT_NAME, String CLIENT_RESIDENCE, String CLIENT_PHONE, String SCHEDULED_DATE) {
        this.DISPATCH_ID = DISPATCH_ID;
        this.TICKET = TICKET;
        this.REQUEST_TYPE = REQUEST_TYPE;
        this.ASSIGNEE = ASSIGNEE;
        this.ASSIGNMENT_DATE = ASSIGNMENT_DATE;
        this.ASSIGNER = ASSIGNER;
        this.LAST_UPDATE = LAST_UPDATE;
        this.STATUS = STATUS;
        this.COMMENTS = COMMENTS;
        this.SCHEDULED_TIME_SLOT = SCHEDULED_TIME_SLOT;
        this.CLIENT_NAME = CLIENT_NAME;
        this.CLIENT_RESIDENCE = CLIENT_RESIDENCE;
        this.CLIENT_PHONE = CLIENT_PHONE;
        this.SCHEDULED_DATE = SCHEDULED_DATE;
    }

    public String getDISPATCH_ID() {
        return DISPATCH_ID;
    }

    public String getTICKET() {
        return TICKET;
    }

    public String getREQUEST_TYPE() {
        return REQUEST_TYPE;
    }

    public String getASSIGNEE() {
        return ASSIGNEE;
    }

    public String getASSIGNMENT_DATE() {
        return ASSIGNMENT_DATE;
    }

    public String getASSIGNER() {
        return ASSIGNER;
    }

    public String getLAST_UPDATE() {
        return LAST_UPDATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getCOMMENTS() {
        return COMMENTS;
    }

    public String getSCHEDULED_TIME_SLOT() {
        return SCHEDULED_TIME_SLOT;
    }

    public String getCLIENT_NAME() {
        return CLIENT_NAME;
    }

    public String getCLIENT_RESIDENCE() {
        return CLIENT_RESIDENCE;
    }

    public String getCLIENT_PHONE() {
        return CLIENT_PHONE;
    }

    public String getSCHEDULED_DATE() {
        return SCHEDULED_DATE;
    }
}
