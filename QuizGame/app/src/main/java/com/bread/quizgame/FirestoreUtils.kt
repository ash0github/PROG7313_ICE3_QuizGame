package com.bread.quizgame

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirestoreUtils {

    private val db = Firebase.firestore

    fun addSampleData() {
        val categories = listOf("Gardening", "Astronomy", "General Knowledge", "Computer Science")
        categories.forEach { category ->
            val data = hashMapOf("name" to category)
            db.collection("categories").document(category).set(data)
                .addOnSuccessListener { Log.d("Firestore", "Category $category added.") }
                .addOnFailureListener { e -> Log.w("Firestore", "Error adding category $category", e) }
        }

        val questions = listOf(
            // Gardening Questions
            Question("What is the process of removing dead or overgrown branches from a plant?", listOf("Pollination", "Pruning", "Germination", "Photosynthesis"), 1, "Gardening"),
            Question("What type of soil is best for most plants?", listOf("Clay", "Sand", "Loam", "Silt"), 2, "Gardening"),
            Question("What is the term for planting seeds indoors before the last frost?", listOf("Mulching", "Composting", "Seed starting", "Weeding"), 2, "Gardening"),
            Question("Which of these is a common garden pest?", listOf("Ladybug", "Earthworm", "Aphid", "Bee"), 2, "Gardening"),
            Question("What is the process of adding organic matter to soil called?", listOf("Fertilizing", "Composting", "Irrigating", "Tilling"), 1, "Gardening"),
            Question("What is the term for a plant that lives for more than two years?", listOf("Annual", "Biennial", "Perennial", "Seasonal"), 2, "Gardening"),
            Question("What is the term for a vine that climbs by tendrils or twining?", listOf("Shrub", "Creeper", "Herb", "Bulb"), 1, "Gardening"),
            Question("What is the process of transferring pollen from one flower to another?", listOf("Germination", "Pollination", "Transpiration", "Respiration"), 1, "Gardening"),
            Question("Which tool is used for loosening soil?", listOf("Shears", "Rake", "Trowel", "Hoe"), 3, "Gardening"),
            Question("What does pH measure in soil?", listOf("Moisture", "Temperature", "Acidity", "Nutrients"), 2, "Gardening"),

            // Astronomy Questions
            Question("What is the largest planet in our solar system?", listOf("Mars", "Jupiter", "Saturn", "Earth"), 1, "Astronomy"),
            Question("What is the name of our galaxy?", listOf("Andromeda", "Triangulum", "Milky Way", "Whirlpool"), 2, "Astronomy"),
            Question("What is a light-year a measure of?", listOf("Time", "Distance", "Speed", "Mass"), 1, "Astronomy"),
            Question("What is the name of the closest star to Earth (excluding the Sun)?", listOf("Sirius", "Alpha Centauri", "Betelgeuse", "Vega"), 1, "Astronomy"),
            Question("What is a black hole?", listOf("A white dwarf", "A neutron star", "A region of spacetime with strong gravity", "A nebula"), 2, "Astronomy"),
            Question("What is the name of the red planet?", listOf("Venus", "Mars", "Mercury", "Uranus"), 1, "Astronomy"),
            Question("What is a comet made of?", listOf("Rock", "Ice and dust", "Gas", "Metal"), 1, "Astronomy"),
            Question("What is the term for a star that suddenly increases greatly in brightness?", listOf("Pulsar", "Quasar", "Nova", "Black dwarf"), 2, "Astronomy"),
            Question("What is the name of Earth's natural satellite?", listOf("Titan", "Europa", "Moon", "Ganymede"), 2, "Astronomy"),
            Question("What is the term for a group of stars forming a recognizable pattern?", listOf("Asteroid", "Meteor", "Constellation", "Galaxy"), 2, "Astronomy"),

            // General Knowledge Questions
            Question("What is the capital of France?", listOf("Rome", "Berlin", "Madrid", "Paris"), 3, "General Knowledge"),
            Question("What is the largest ocean on Earth?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3, "General Knowledge"),
            Question("How many continents are there?", listOf("5", "6", "7", "8"), 2, "General Knowledge"),
            Question("What is the chemical symbol for gold?", listOf("Ag", "Au", "Fe", "Cu"), 1, "General Knowledge"),
            Question("Who wrote 'Romeo and Juliet'?", listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"), 2, "General Knowledge"),
            Question("What is the largest mammal on Earth?", listOf("Elephant", "Giraffe", "Blue whale", "Lion"), 2, "General Knowledge"),
            Question("What is the currency of Japan?", listOf("Yuan", "Won", "Yen", "Ringgit"), 2, "General Knowledge"),
            Question("What is the boiling point of water in Celsius?", listOf("50", "100", "150", "200"), 1, "General Knowledge"),
            Question("What is the tallest mountain in the world?", listOf("Kilimanjaro", "Everest", "McKinley", "Matterhorn"), 1, "General Knowledge"),
            Question("What is the name of the first man to walk on the moon?", listOf("Yuri Gagarin", "Alan Shepard", "Neil Armstrong", "Buzz Aldrin"), 2, "General Knowledge"),

            // Computer Science Questions
            Question("What does CPU stand for?", listOf("Central Processing Unit", "Computer Peripheral Unit", "Control Program Utility", "Common Protocol Usage"), 0, "Computer Science"),
            Question("What is HTML used for?", listOf("Programming", "Database management", "Web page structure", "Image editing"), 2, "Computer Science"),
            Question("What is RAM?", listOf("Read-only memory", "Random access memory", "Remote access memory", "Rotational access memory"), 1, "Computer Science"),
            Question("What is an IP address?", listOf("Internet Protocol", "Internal Processor", "Input Port", "Image Processing"), 0, "Computer Science"),
            Question("What does URL stand for?", listOf("Universal Resource Locator", "Uniform Remote Location", "Unified Retrieval Link", "User Request Line"), 0, "Computer Science"),
            Question("What is a database?", listOf("A programming language", "A collection of organized data", "A type of hardware", "A network protocol"), 1, "Computer Science"),
            Question("What is a firewall?", listOf("A computer virus", "A security system", "A programming tool", "A data backup"), 1, "Computer Science"),
            Question("What is an algorithm?", listOf("A computer program", "A step-by-step procedure", "A type of hardware", "A network device"), 1, "Computer Science"),
            Question("What is a compiler?", listOf("A text editor", "A program that translates code", "A graphics tool", "A network analyzer"), 1, "Computer Science"),
            Question("What is the name of the coding language used for android development?", listOf("Swift", "Python", "Kotlin", "Javascript"), 2, "Computer Science")
        )

        questions.forEach { question ->
            db.collection("questions").add(question)
                .addOnSuccessListener { Log.d("Firestore", "Question added.") }
                .addOnFailureListener { e -> Log.w("Firestore", "Error adding question", e) }
        }
    }
}