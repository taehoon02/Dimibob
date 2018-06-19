package io.github.taehoon02.dimibob

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import khttp.responses.Response
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import khttp.delete as httpDelete

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

            var breakfast: String
            var lunch: String
            var dinner: String
            var snack: String

            // Today
            val today : Calendar = Calendar.getInstance()
            val format = SimpleDateFormat("yyyyMMdd").format(today.time)
            var url = "https://api.dimigo.in/dimibobs/" + format

            // Json Parser
            val response : Response = khttp.get(url)
            val obj : JSONObject = response.jsonObject

            breakfast = obj["breakfast"] as String
            lunch = obj["lunch"] as String
            dinner = obj["dinner"] as String
            snack = obj["snack"] as String

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.dimibob_today)
            views.setTextViewText(R.id.breakfast_show, breakfast)
            views.setTextViewText(R.id.lunch_show, lunch)
            views.setTextViewText(R.id.dinner_show, dinner)
            views.setTextViewText(R.id.snack_show, snack)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

