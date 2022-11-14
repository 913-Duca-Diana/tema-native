package com.example.songscollection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var songAdapter: CollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        songAdapter= CollectionAdapter(mutableListOf())

        rvCollectionItems.adapter= songAdapter
        rvCollectionItems.layoutManager=LinearLayoutManager(this)

        btnAddSong.setOnClickListener {
            val songTitle= etCollectionTitle.text.toString()
            if(songTitle.isNotEmpty()){
                val song = Collection(songTitle)
                songAdapter.addSong(song)
                etCollectionTitle.text.clear()
            }
        }
        btnDeleteSong.setOnClickListener {
            songAdapter.deleteSongs()
        }
    }
}