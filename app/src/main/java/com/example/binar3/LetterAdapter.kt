package com.example.binar3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binar3.databinding.ItemLetterBinding

// LetterAdapter.kt
class LetterAdapter(private val letters: List<String>, private val clickListener: (String) -> Unit) : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    class LetterViewHolder(val binding: ItemLetterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val binding = ItemLetterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LetterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.binding.letterText.text = letters[position]
        holder.itemView.setOnClickListener { clickListener(letters[position]) }
    }

    override fun getItemCount(): Int = letters.size
}
