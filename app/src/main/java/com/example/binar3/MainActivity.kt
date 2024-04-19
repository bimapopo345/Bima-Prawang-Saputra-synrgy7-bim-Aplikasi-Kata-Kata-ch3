package com.example.binar3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar


// MainActivity.kt
class MainActivity : AppCompatActivity() {
    private lateinit var lettersRecyclerView: RecyclerView
    private lateinit var wordsRecyclerView: RecyclerView
    private lateinit var lettersAdapter: RecyclerView.Adapter<*>
    private lateinit var wordsAdapter: RecyclerView.Adapter<*>
    private lateinit var toggleButton: ImageButton
    private lateinit var toolbar: Toolbar
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        toggleButton = findViewById(R.id.toggleLayoutButton)
        toggleButton.setOnClickListener { toggleLayout() }

        lettersRecyclerView = findViewById(R.id.lettersRecyclerView)
        wordsRecyclerView = findViewById(R.id.wordsRecyclerView)
        setupRecyclerViews()
    }

    override fun onBackPressed() {
        if (wordsRecyclerView.visibility == View.VISIBLE) {
            wordsRecyclerView.visibility = View.GONE
            lettersRecyclerView.visibility = View.VISIBLE
            toolbar.title = "List Letter"
            toggleButton.visibility = View.VISIBLE // Tampilkan tombol toggle grid
        } else {
            super.onBackPressed()
        }
    }

    private fun setupRecyclerViews() {
        // Assuming LetterAdapter and WordsAdapter are properly implemented
        lettersAdapter = LetterAdapter(listOf("A", "B", "C")) { letter ->
            showWords(letter)
        }
        wordsAdapter = WordsAdapter(listOf()) // Start with an empty list
        lettersRecyclerView.adapter = lettersAdapter
        wordsRecyclerView.adapter = wordsAdapter
        lettersRecyclerView.layoutManager = LinearLayoutManager(this)
        wordsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showWords(letter: String) {
        val words = when (letter) {
            "A" -> listOf("Ayam", "Alfamart", "Aspal")
            "B" -> listOf("Bayam", "Bali", "Batik")
            "C" -> listOf("Cicak", "Capung", "Cerita")
            else -> emptyList()
        }
        (wordsAdapter as WordsAdapter).updateWords(words)
        lettersRecyclerView.visibility = View.GONE
        wordsRecyclerView.visibility = View.VISIBLE
        toolbar.title = "List Letter: $letter"
        toggleButton.visibility = View.GONE // Sembunyikan tombol toggle grid
    }


    private fun toggleLayout() {
        if (isLinearLayoutManager) {
            toggleButton.setImageResource(R.drawable.ic_grid_on_24) // Grid icon
            lettersRecyclerView.layoutManager = GridLayoutManager(this, 2) // Mengubah ke grid layout
        } else {
            toggleButton.setImageResource(R.drawable.ic_linear_layout) // Linear icon
            lettersRecyclerView.layoutManager = LinearLayoutManager(this) // Kembali ke linear layout
        }
        isLinearLayoutManager = !isLinearLayoutManager
    }
}


