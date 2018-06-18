package io.github.taehoon02.dimibob

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val check = findViewById<View>(R.id.check) as Button
        check.setOnClickListener {
            // Toast.makeText(this@MainActivity, "디미밥을 이용해주셔서 감사합니다.", Toast.LENGTH_SHORT)
            finish()
        }

    }
}
