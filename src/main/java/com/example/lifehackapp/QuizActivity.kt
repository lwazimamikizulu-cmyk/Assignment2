package com.example.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {

    var index = 0
    var score = 0
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
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
    val explanations = arrayOf(
        "Myth: While you need good nutrition, you don't actually need to double your caloric intake.",
        "Myth: Heartburn is caused by hormones and the baby taking up space, not the baby's hair growth.",
        "Hack: This is good for back pain relief.",
        "Hack: Staying hydrated helps with headaches, swelling and fatigue.",
        "Myth: Craving sweets (girl) or salty/sour foods is just a fun way to guess, not a scientific method.",
        "Myth: Its generally safe in a healthy pregnancy.",
        "Myth: Cravings are mostly hormonal, not nutritional signals.",
        "Hack: Gentle movements can reduce aches and boost moods."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        loadQuestion()

        hackButton.setOnClickListener { checkAnswer(true) }
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            index++

            if (index < questions.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }
    fun loadQuestion() {
        questionText.text = questions[index]
    }

    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            feedbackText.text = "Spot on! 🎉\n${explanations[index]}"
            score++
        } else {
            feedbackText.text = "Nope! ❌\n${explanations[index]}"
        }
}
}