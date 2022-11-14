package com.example.songscollection

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_collection.view.*


class CollectionAdapter(
    private val songs:MutableList<Collection>
) : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

  class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_collection,
                parent,
                false
            )
        )
    }
    fun deleteSongs(){
        songs.removeAll { song ->
            song.isChecked
        }
        notifyDataSetChanged()
    }
    fun addSong(song: Collection){
        songs.add(song)
        notifyItemInserted(songs.size-1)
    }

    private fun toggleStrikeThrough(tvCollectionTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvCollectionTitle.paintFlags = tvCollectionTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvCollectionTitle.paintFlags = tvCollectionTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val curSong=songs[position]
        holder.itemView.apply {
            tvCollectionTitle.text =curSong.title
            cbDone.isChecked =curSong.isChecked
            toggleStrikeThrough(tvCollectionTitle,curSong.isChecked)
            cbDone.setOnCheckedChangeListener {  _ , isChecked ->
                toggleStrikeThrough(tvCollectionTitle, isChecked)
                curSong.isChecked=!curSong.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}