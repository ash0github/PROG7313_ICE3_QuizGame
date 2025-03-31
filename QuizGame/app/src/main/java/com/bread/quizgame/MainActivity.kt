package com.bread.quizgame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private var questions: List<Question> = listOf()
    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var resultTextView: TextView
    private lateinit var categoryButtons: List<Button>
    private lateinit var questionView: View
    private lateinit var categoryView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FirestoreUtils.addSampleData()

        questionTextView = findViewById(R.id.questionTextView)
        option1Button = findViewById(R.id.option1Button)
        option2Button = findViewById(R.id.option2Button)
        option3Button = findViewById(R.id.option3Button)
        option4Button = findViewById(R.id.option4Button)
        resultTextView = findViewById(R.id.resultTextView)
        categoryButtons = listOf(
            findViewById(R.id.categoryButton1),
            findViewById(R.id.categoryButton2),
            findViewById(R.id.categoryButton3),
            findViewById(R.id.categoryButton4)
        )
        questionView = findViewById(R.id.questionView)
        categoryView = findViewById(R.id.categoryView)
        questionView.visibility = View.GONE

        categoryButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                loadQuestions(getCategories()[index])
            }
        }
    }

    private fun getCategories(): List<String> {
        return listOf("Gardening", "Astronomy", "General Knowledge", "Computer Science")
    }

    private fun loadQuestions(category: String) {
        db.collection("questions").whereEqualTo("category", category).get()
            .addOnSuccessListener { result ->
                questions = result.toObjects(Question::class.java)
                currentQuestionIndex = 0
                score = 0
                categoryView.visibility = View.GONE
                questionView.visibility = View.VISIBLE
                showQuestion()
            }
    }

    private fun showQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            questionTextView.text = question.questionText
            option1Button.text = question.options[0]
            option2Button.text = question.options[1]
            option3Button.text = question.options[2]
            option4Button.text = question.options[3]

            option1Button.setOnClickListener { checkAnswer(0) }
            option2Button.setOnClickListener { checkAnswer(1) }
            option3Button.setOnClickListener { checkAnswer(2) }
            option4Button.setOnClickListener { checkAnswer(3) }
        } else {
            showResults()
        }
    }

    private fun checkAnswer(selectedIndex: Int) {
        if (selectedIndex == questions[currentQuestionIndex].correctAnswerIndex) {
            score++
        }
        currentQuestionIndex++
        showQuestion()
    }

    private fun showResults() {
        resultTextView.text = "Previous Score: $score"
        saveScoreToFirestore()
        categoryView.visibility = View.VISIBLE
        questionView.visibility = View.GONE
    }

    private fun saveScoreToFirestore() {
        val scoreObj = Score(category = questions[0].category, score = score)
        db.collection("scores").add(scoreObj)
    }
}