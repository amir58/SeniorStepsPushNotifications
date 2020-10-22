package com.example.seniorstepspushnotifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFCMToken();
    }


    private void getFCMToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e("newToken", newToken);

        });

    }


    private JsonObject buildNotificationPayload() {

        // { Object } , [ Array ]
        // compose notification json payload
        JsonObject payload = new JsonObject();
        payload.addProperty("to", "token");

        // compose data payload here
        JsonObject data = new JsonObject();
        data.addProperty("title", "Title message");
        data.addProperty("message", "Content message");
//        data.addProperty(key.getText().toString(), value.getText().toString());
        // add data payload
        payload.add("data", data);

//        {
//            "to":"token_value",
//                "data":{
//            "title":"title_value",
//                    "message":"message value"
//        }
//        }

        return payload;
    }

    private void pushNotification() {
        JsonObject payload = buildNotificationPayload();
        // send notification to receiver ID
        ApiClient.getApiService().sendNotificationByJsonObject(payload).enqueue(
                new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Notification send successful",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        String errorMessage = t.getMessage();
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}