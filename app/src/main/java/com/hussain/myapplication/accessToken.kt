package com.hussain.myapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
//import com.google.auth.oauth2.GoogleCredentials
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

/*
object AccessToken {

    private val  firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging"

    fun getAccessToken():String?{
        try{
            val jsonString  = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"myapplication-bbee5\",\n" +
                    "  \"private_key_id\": \"630f2e8eb7100d88099cfedb2fe34d1c7aca02ee\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCcVW9DKyjjgxI2\\n1/xI8ZJJbyjvFLe/TeUzurk8uz+ZkHCNQy5I5nRmwntbWdaoGMJPnhvQEuwkPmq8\\nabAbr505Tv58uFSUV0XD3COFRMuGp04OVVD9hhJKfzyNuklx/+MJxTqHwUUAz+0B\\n51L23UejzRvXogLtxOnoZQXVukFYQ/+ICJqYHu4n/PVW0V1rZGhNsRQ5Z8Xd7SfP\\nxnYTzmeVJdPZoMhB6lvd0N0haBghtd8OGGNrrSKXcJtp9t4JTffrE2RCABeGXec9\\nSOTW+jwwVv4hhKL+suC2crIZ5OP0rUs7e6xRzVPdcyuCz5w2Xkj2sSHGJSz30K49\\ng7YEQGahAgMBAAECgf93pYtPGiu5CV9Z9K4eGtmdX3iHQeaUgHFNHeMXGoKabMk3\\nKUC12mp2cEcoqvZ2FZI2jQiMPsSBGKQSfxtL6nj8m1B5njmyg6Mz5bnpt7UXNoRZ\\nbpewRc6fOrHau8U/fQUDPUcxDuGuCsmWuLaY6NHOCJ7+cVKnLBrjbthVsq7AIFgr\\nudmyd17SBVXV9fkRXg3kparqK07YhqzcvjyXuOt9GtAiSoxSvKkdsNavrNHbaqwV\\nVQYpOatYs3mkDQQ2BFVyqseKiphL4vS5eAB9D33Ke/jQtvfIfMZEZr2aBxRPjWDS\\nBoqZPqOH0wcN7G9RRb4CS/PBX9dsLbISi3Vf9wECgYEAzsldApRXBml+aQaFwNoi\\n7vHjfM7jEHez7q1EAQH1xpAuNmd7DCi2w2CN/xSN9S7VG7UASJCEtXvMib24s59b\\nSDCGaclPoVEu87XFTyZ0aiEwD5Va1W4q27RKBH4+jSYVPYhkMQAvjcbtx5i/XW8A\\nB5b49I5LnVVZHQBajgS/IQECgYEAwYoxBGMFkWDcLO2SkW/4DJVRB8WKeWlvRS0f\\no2IQy/h7QZ3yKHOxWcYRQxCTvDOrCAbQINqIXtv6Gkpq2P5zDoD2pxfy02Eg9OGl\\nV6d8R1SxWPGy4q1gPg7DjjRj4zFX2dHCa2+647ZXzQKA9EzZChZ8IxQQJhQIwnCN\\nkzDHpaECgYEAll0349NIAKKfR/F1Owmjk0qS18N8uxG/5hNZur3ZINzgrbDLecj1\\nNNa2CkDBBQNb9TzFiv6DcZAm6BXsA7kh4Dlg0/WNkMj+sjp/0hEtX6roTryVA1OL\\nmJzdoazHAU4q7caKzFLNQXPgnV3F8UPwqpQuTW5XL33LQdpq/pAXBwECgYBCSgHV\\n/dCXZbSkEs8jz1UHglEZt1hhk3JQIE9414rnQfl9lQqvPkkddZDOBqyDRrMq8aJx\\nzkftLa0xOhH0ttpTumpA+xvpuJfgKDVIm44epGyGCWr65uVS/B2Q5HLeCzWK3qSX\\nxdIeUoMqLdRbWMLyCJeGMEYDtNL3PjPPmWKYYQKBgQCJUY3eqUeij4bLp2vbQdCH\\neBjtLcAjHxovdTqzDLQyu+ejf/Fa+Y3HvJj03Mw/f9orl5RToMa3bAN8hJulWP1a\\n6AQmg2hxzurKEliBVibbii2oNjOZvAhHKuB9siFhmMW14r0LdKM24/6gcn45Pqzf\\nqzOejyxKF4tNlWSaPBTGUw==\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-b30n5@myapplication-bbee5.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"108085829356409209204\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-b30n5%40myapplication-bbee5.iam.gserviceaccount.com\",\n" +
                    "  \"universe_domain\": \"googleapis.com\"\n" +
                    "}\n"

            val stream = ByteArrayInputStream(jsonString.toByteArray(StandardCharsets.UTF_8))

            val googleCredential = GoogleCredentials.fromStream(stream)
                .createScoped(arrayListOf(firebaseMessagingScope))
            Log.e("googleCredential","googleCredential:: " + googleCredential );


            googleCredential.refresh();

            val token = googleCredential.accessToken.tokenValue
            Log.d(TAG, "Access Token: $token")  // Log the access token
            Log.e("googleCredential","googleCredential:: " + token);

            token

            return googleCredential.accessToken.tokenValue

            }catch (e:IOException){
                return null
            }
        }

}*/


import com.google.auth.oauth2.GoogleCredentials
import java.io.FileInputStream
import java.io.InputStream

/*object AccessTokenUtil {
    fun getAccessToken(context: Context): String {
        try {
            val inputStream: InputStream = context.resources.openRawResource(R.raw.myapplication2)  // Accessing raw resource
            val credentials = GoogleCredentials
                .fromStream(inputStream)
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
            credentials.refreshIfExpired()
            val token = credentials.accessToken.tokenValue
            Log.d("AccessTokenUtil", "Access token generated: $token")
            return credentials.accessToken.tokenValue
        } catch (e: Exception) {
            throw RuntimeException("Failed to load access token: ${e.message}")
        }
    }
}*/
//______________________________________________________________
object AccessTokenUtil {
    fun getAccessToken(context: Context): String {
        try {
            val inputStream: InputStream = context.resources.openRawResource(R.raw.myapplication2)
            val credentials = GoogleCredentials
                .fromStream(inputStream)
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
            credentials.refreshIfExpired()

            val token = credentials.accessToken.tokenValue
            Log.d("FCM", "Access token generated: $token")

            return credentials.accessToken.tokenValue
        } catch (e: Exception) {
            throw RuntimeException("Failed to load access token: ${e.message}")
        }
    }
}
