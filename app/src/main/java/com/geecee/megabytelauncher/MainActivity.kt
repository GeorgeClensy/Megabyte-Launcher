@file:Suppress("DEPRECATION")

package com.geecee.megabytelauncher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.view.View

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pm = packageManager
        val apps = pm.queryIntentActivities(Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }, 0)

        val appList = findViewById<LinearLayout>(R.id.app_list)

        for (resolveInfo in (apps.sortedBy { it.loadLabel(pm).toString() }).filter { it.activityInfo.packageName != "com.geecee.megabytelauncher" }) {
            val appName = resolveInfo.loadLabel(pm).toString()
            val launchIntent = pm.getLaunchIntentForPackage(resolveInfo.activityInfo.packageName)

            val textView = TextView(this).apply {
                text = appName
                textSize = 24f
                setPadding(8, 20, 8, 20)
                setOnClickListener {
                    launchIntent?.let { startActivity(it) }
                }
                setTextColor(0xFFFFFFFF.toInt())
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            appList.addView(textView, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ))
        }
    }

    // Hide status bar and nav bar
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
    }

}
