package com.hussain.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.messaging.FirebaseMessaging
import com.hussain.myapplication.api.NotificationAPI
import com.hussain.myapplication.model.Notification
import com.hussain.myapplication.model.NotificationData
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.btn_send_notification);
        btn.setOnClickListener(){
            sendNotification()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Dexter.withContext(applicationContext)
                .withPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        // Handle the case when permission is granted
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        // Handle the case when permission is denied
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        // Show rationale for the permission request
                        p1?.continuePermissionRequest()
                    }
                }).check()
        }

        val policy  = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        FirebaseMessaging.getInstance().subscribeToTopic("hussain")


    }


    */
/*private fun sendNotification(){
        val notification = Notification(
            message = NotificationData(
                "hussain",
               hashMapOf("title" to "this is the notificatioon title",
                "body" to "this is the firebase message v1 notification" )
            )
        )

        Log.e("googleCredential","notification:: " + notification );


        NotificationAPI.sendNotification().notification(notification).enqueue(
            object: Callback<Notification> {
*//*

    */
/*
                override fun onResponse(p0: Call<Notification>, p1: Response<Notification>) {
                    Toast.makeText(
                        this@MainActivity,"Notification send..",Toast.LENGTH_LONG
                    ).show()
                }
*//*

    */
/*
override fun onResponse(call: Call<Notification>, response: Response<Notification>) {
    if (response.isSuccessful) {
        // Handle the successful response
        Log.d("NotificationResponse", "Success: ${response.body()}")
        Toast.makeText(
            this@MainActivity,
            "Notification sent successfully: ${response.body()}",
            Toast.LENGTH_LONG
        ).show()
    } else {
        // Handle the error response
        Log.e("NotificationResponse", "Error: ${response.errorBody()?.string()}")
        Toast.makeText(
            this@MainActivity,
            "Failed to send notification: ${response.errorBody()?.string()}",
            Toast.LENGTH_LONG
        ).show()
    }
}

                override fun onFailure(p0: Call<Notification>, p1: Throwable) {
                    Toast.makeText(
                        this@MainActivity,"error occure:: ${p1.message}",Toast.LENGTH_LONG
                    ).show()
                }
            }
        )

    }*//*


    private fun sendNotification() {
        val notification = Notification(
            message = NotificationData(
                "hussain",
                hashMapOf(
                    "title" to "This is the notification title",
                    "body" to "This is the Firebase message v1 notification"
                )
            )
        )

        try {
            val accessToken = "Bearer ${AccessTokenUtil.getAccessToken(this)}"  // Pass the context here

            NotificationAPI.sendNotification().notification(notification, accessToken).enqueue(
                object : Callback<Notification> {
                    override fun onResponse(call: Call<Notification>, response: Response<Notification>) {
                        if (response.isSuccessful) {
                            Log.d("NotificationResponse", "Success: ${response.body()}  ")
                            Toast.makeText(this@MainActivity, "Notification sent successfully.", Toast.LENGTH_LONG).show()
                        } else {
                            Log.e("NotificationResponse", "Error: ${response.errorBody()?.string()}")
                            Toast.makeText(this@MainActivity, "Failed: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Notification>, t: Throwable) {
                        Log.e("NotificationResponse", "Failure: ${t.message}")
                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("AccessTokenError", "Failed to generate access token: ${e.message}")
            Toast.makeText(this, "Error generating access token: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


}*/
//____________________________________________________________________________________________________


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnSendNotification = findViewById<Button>(R.id.btn_send_notification)
        btnSendNotification.setOnClickListener {
            sendNotification()
        }

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Dexter.withContext(applicationContext)
                .withPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        Log.d("Permission", "Notification permission granted.")
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Log.e("Permission", "Notification permission denied.")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                }).check()
        }

        // Set ThreadPolicy to allow network operations in main thread (only for development/testing)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Subscribe to a topic for notifications
        FirebaseMessaging.getInstance().subscribeToTopic("hussain")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM", "Subscribed to topic: hussain")
                } else {
                    Log.e("FCM", "Failed to subscribe to topic: ${task.exception?.message}")
                }
            }

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e("FCM", "Fetching FCM token failed", task.exception)
                    return@addOnCompleteListener
                }

                // Get the FCM token
                val token = task.result
                Log.d("FCM", "FCM Token: $token")
            }

    }

    private fun sendNotification() {
        val notification = Notification(
            message = NotificationData(
                "hussain",
                hashMapOf(
                    "title" to "This is the notification title",
                    "body" to "This is the Firebase message v1 notification"
                )
            )
        )

        try {
            val accessToken = "Bearer ${AccessTokenUtil.getAccessToken(this)}"

            NotificationAPI.service.notification(notification, accessToken).enqueue(
                object : Callback<Notification> {
                    override fun onResponse(call: Call<Notification>, response: Response<Notification>) {
                        if (response.isSuccessful) {
                            Log.d("NotificationResponse", "Success: ${response.body()}")
                            Toast.makeText(this@MainActivity, "Notification sent successfully.", Toast.LENGTH_LONG).show()
                        } else {
                            Log.e("NotificationResponse", "Error: ${response.errorBody()?.string()}")
                            Toast.makeText(this@MainActivity, "Failed: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Notification>, t: Throwable) {
                        Log.e("NotificationResponse", "Failure: ${t.message}")
                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("AccessTokenError", "Failed to generate access token: ${e.message}")
            Toast.makeText(this, "Error generating access token: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

}
