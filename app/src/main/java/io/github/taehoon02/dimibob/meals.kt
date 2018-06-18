package io.github.taehoon02.dimibob

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class meals : AsyncTask<Void, Void, Void>() {
    var data = ""
    var breakfast: String = ""
    var lunch: String = ""
    var dinner: String = ""
    var snack:String = ""

    override fun doInBackground(vararg voids: Void): Void? {
        try {
            val today : Calendar = Calendar.getInstance()
            val format = SimpleDateFormat("yyyyMMdd").format( today.time )

            val url = URL("https://api.dimigo.in/dimibobs/" + format)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""
            while (line != null) {
                line = bufferedReader.readLine()
                data += line
            }

            val jsonObject = JSONObject(data)
            breakfast = jsonObject.get("breakfast") as String
            if (breakfast == null) breakfast = "급식 정보가 없습니다."
            lunch = jsonObject.get("lunch") as String
            if (lunch == null) lunch = "급식 정보가 없습니다."
            dinner = jsonObject.get("dinner") as String
            if (dinner == null) dinner = "급식 정보가 없습니다."
            snack = jsonObject.get("snack") as String
            if (snack == null) snack = "급식 정보가 없습니다."

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }



}