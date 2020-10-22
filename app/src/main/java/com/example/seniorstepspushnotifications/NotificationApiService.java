package com.example.seniorstepspushnotifications;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificationApiService {

    @Headers({
            "Authorization: key=" + "AAAAWhIEN7c:APA91bHCELgQbB8ZVhV-3k17awOUcdmamuhDYES94VSEDD-1kXmH7Llz7LTqGcNN_IyWSEC4uled6cvgIc6GsYNWAZFgn3yADP3LDMkST4U-zuYXJ9MxyjvKiXVyvJ8mGtCR4TDjWTXm",
            "Content-Type: application/json"
    })
    @POST("fcm/send")
    Call<JsonObject> sendNotificationByJsonObject(@Body JsonObject payload);

    @Headers({
            "Authorization: key=" + "AAAAWhIEN7c:APA91bHCELgQbB8ZVhV-3k17awOUcdmamuhDYES94VSEDD-1kXmH7Llz7LTqGcNN_IyWSEC4uled6cvgIc6GsYNWAZFgn3yADP3LDMkST4U-zuYXJ9MxyjvKiXVyvJ8mGtCR4TDjWTXm",
            "Content-Type: application/json"
    })
    @POST("fcm/send")
    Call<ResponseBody> sendNotificationByModelClass(@Body RequestNotificaton  requestNotificaton );


}
