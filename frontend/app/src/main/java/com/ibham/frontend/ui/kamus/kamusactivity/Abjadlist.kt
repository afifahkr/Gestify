// Abjadlist.kt
package com.ibham.frontend.ui.kamus.kamusactivity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibham.frontend.R
import com.ibham.frontend.ui.kamus.adapter.Abjadlist_adapter

class Abjadlist : AppCompatActivity() {

    private val recyclerViewList: ArrayList<Recyclerview_list> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var recyclerViewAdapter: Abjadlist_adapter
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dictionary_list)

        searchView = findViewById(R.id.searchView)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        noResultTextView = findViewById(R.id.noResultTextView)

        recyclerViewList.add(Recyclerview_list(R.drawable.a, "a"))
        recyclerViewList.add(Recyclerview_list(R.drawable.b, "b"))
        recyclerViewList.add(Recyclerview_list(R.drawable.c, "c"))
        recyclerViewList.add(Recyclerview_list(R.drawable.d, "d"))
        recyclerViewList.add(Recyclerview_list(R.drawable.e, "e"))
        recyclerViewList.add(Recyclerview_list(R.drawable.f, "f"))
        recyclerViewList.add(Recyclerview_list(R.drawable.g, "g"))
        recyclerViewList.add(Recyclerview_list(R.drawable.h, "h"))
        recyclerViewList.add(Recyclerview_list(R.drawable.i, "i"))
        recyclerViewList.add(Recyclerview_list(R.drawable.j, "j"))

        recyclerViewAdapter = Abjadlist_adapter(recyclerViewList, this)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun filterList(text: String?) {
        val filteredList = ArrayList<Recyclerview_list>()
        for (item in recyclerViewList) {
            if (item.getItemName().toLowerCase().contains(text?.toLowerCase().orEmpty())) {
                filteredList.add(item)
            }
        }

        if (filteredList.isEmpty()) {
            // Tampilkan noResultTextView jika daftar kosong
            noResultTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            Toast.makeText(this, "Huruf tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            // Sembunyikan noResultTextView dan tampilkan daftar
            noResultTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerViewAdapter.setFilteredList(filteredList)
        }
    }
}
