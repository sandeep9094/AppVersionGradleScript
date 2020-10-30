package com.developidea.gradlebuildexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBuildTextView : TextView = findViewById(R.id.textview)
        appBuildTextView.text = "App Build Version : ${BuildConfig.VERSION_NAME}"
    }
}