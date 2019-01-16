package com.jackoyee.xprome.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketsResponse {

    @SerializedName("data")
    private  List<Tickets>data;
    @SerializedName("request_timestamp")
    private String request_timestamp;
    @SerializedName("response")
    private boolean response;
    @SerializedName("request_id")
    private  String request_id;
    @SerializedName("requester_uid")
    private  String requester_uid;
    @SerializedName("status")
    private  int status;

//    public TicketsResponse() {
//    }


    public TicketsResponse(List<Tickets> data) {
        this.data = data;
    }

    public List<Tickets> getData() {
        return data;
    }

    public TicketsResponse(String request_timestamp, boolean response, String request_id, String requester_uid, int status) {
        this.request_timestamp = request_timestamp;
        this.response = response;
        this.request_id = request_id;
        this.requester_uid = requester_uid;
        this.status = status;
    }

    public String getRequest_timestamp() {
        return request_timestamp;
    }

    public boolean isResponse() {
        return response;
    }

    public String getRequest_id() {
        return request_id;
    }

    public String getRequester_uid() {
        return requester_uid;
    }

    public int getStatus() {
        return status;
    }
}
