package io.github.taehoon02.dimibob

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.view.View
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast

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

            // Use Class
            val meals = meals()

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.dimibob_today)
            views.setTextViewText(R.id.breakfast_show, meals.breakfast)
            views.setTextViewText(R.id.lunch_show, meals.lunch)
            views.setTextViewText(R.id.dinner_show, meals.dinner)
            views.setTextViewText(R.id.snack_show, meals.snack)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

