package com.example.lifehackapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        val layout = findViewById<LinearLayout>(R.id.reviewLayout)

        val questions = arrayOf(
            "You need to eat double what you normally do so that the baby can also have food to eat",
            "The baby's hair growth causes heartburn for the mother.",
            "Placing a tennis ball in a tube sock to roll against a wall relieves back pain",
            "Staying hydrated is crucial during pregnancy.",
            "Craving salty foods means you're going to give birth to a boy.",
            "No sex during pregnancy.",
            "Cravings tell you what your body needs.",
            "Gentle movements assist in reducing aches."
        )

        val answers = arrayOf(false, false, true, true, false, false, false, true)

        for (i in questions.indices) {
            val tv = TextView(this)
            val answerText = if (answers[i]) "Hack" else "Myth"
            tv.text = "${questions[i]}\nAnswer: $answerText\n"
            tv.setPadding(0, 0, 0, 20)
            layout.addView(tv)
        }
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}