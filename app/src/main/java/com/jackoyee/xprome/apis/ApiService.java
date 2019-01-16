package com.jackoyee.xprome.apis;

import com.jackoyee.xprome.model.LoginResponse;
import com.jackoyee.xprome.model.TicketsResponse;
import com.jackoyee.xprome.model.UploadsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

//    String URL = "http://172.29.200.28/dp/rest/";

    @FormUrlEncoded
    @POST("safOauth/2567a5ec9705eb7ac2c984033e06189d/")
    Call<LoginResponse>userLogin(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("supTicketUpdate/2567a5ec9705eb7ac2c984033e06189d")
          Call<UploadsResponse> uploadData(
            @Field("ticket") String ticket,
            @Field("current_session_key") String current_session_key,
            @Field("client_loc_lat") String client_loc_lat
 );

    @GET("viewMyTickets/2567a5ec9705eb7ac2c984033e06189d/{current_session_key}")
    Call<TicketsResponse> viewMyTickets( @Path("current_session_key") String current_session_key,
                                        @Query("status") String status,
                                        @Query("request_type") String request_type,
                                        @Query("company") String company);

    //  Call<UploadsResponse> uploadData(String status, String client_loc_long, String client_loc_lat);

    // Call<LoginResponse> uploadData(String status, String client_loc_long, String client_loc_lat);
}

//    @FormUrlEncoded
//    @POST("supTicketUpdate")
//    Call<UploadsResponse>uploadData(
//            @Field("ticket") String ticket,
//            @Field("current_session_key")String current_session_key,
//            @Field("client_loc_lat") String client_loc_lat,
//            @Field("client_loc_long") String client_loc_long,
//            @Field("status") String status,
//            @Field("comment") String comment
//    );
