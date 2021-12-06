package com.ozbaykus.pagingexample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozbaykus.pagingexample.data.model.Character
import com.ozbaykus.pagingexample.databinding.ItemSearchResultBinding
import javax.inject.Inject

class CharactersListAdapter @Inject constructor() :
    PagingDataAdapter<Character, CharactersListAdapter.CharactersViewHolder>(CharacterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    inner class CharactersViewHolder(
        private val binding: ItemSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Character?) {
            result?.let {
                Glide.with(itemView.context).load(result.image).into(binding.imageView)
                binding.textViewName.text = result.name
                binding.textViewSpecies.text = result.species
                binding.textViewGender.text = result.gender.name
            }
        }
    }
}

