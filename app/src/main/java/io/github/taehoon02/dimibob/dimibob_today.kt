package io.github.taehoon02.dimibob

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class dimibob_today : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            var breakfast = ""
            var lunch = ""
            var dinner = ""
            var snack = ""

            // Json Parser
            val today : Calendar = Calendar.getInstance()
            val format = SimpleDateFormat("yyyyMMdd").format(today.time)
            val url = "https://api.dimigo.in/dimibobs/" + format

            val request = Request.Builder().url(url).build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()
                    println(body)

                    val jsonObject = JSONObject(body)
                    breakfast = jsonObject.get("breakfast") as String
                    if (breakfast == null) breakfast = "급식 정보가 없습니다."
                    lunch = jsonObject.get("lunch") as String
                    if (lunch == null) lunch = "급식 정보가 없습니다."
                    dinner = jsonObject.get("dinner") as String
                    if (dinner == null) dinner = "급식 정보가 없습니다."
                    snack = jsonObject.get("snack") as String
                    if (snack == null) snack = "급식 정보가 없습니다."

                    println(breakfast)

                    val views = RemoteViews(context.packageName, R.layout.dimibob_today)
                    views.setTextViewText(R.id.breakfast_show, breakfast)
                    views.setTextViewText(R.id.lunch_show, lunch)
                    views.setTextViewText(R.id.dinner_show, dinner)
                    views.setTextViewText(R.id.snack_show, snack)

                    appWidgetManager.updateAppWidget(appWidgetId, views)
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                }
            })
        }
    }
}

