package com.example.binar3

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binar3.databinding.ItemWordBinding

class WordsAdapter(private var words: List<String>) : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: String, clickListener: (String) -> Unit) {
            binding.wordText.text = word
            binding.root.setOnClickListener { clickListener(word) }  // Menetapkan listener untuk menangani klik pada item
        }
    }

    fun updateWords(newWords: List<String>) {
        words = newWords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWordBinding.inflate(inflater, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(words[position]) { word ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$word"))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = words.size
}
