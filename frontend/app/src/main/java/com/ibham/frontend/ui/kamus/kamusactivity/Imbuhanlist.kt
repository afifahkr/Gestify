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
import com.ibham.frontend.ui.kamus.adapter.Imbuhanlist_adapter

class Imbuhanlist : AppCompatActivity() {

    private val recyclerViewList: ArrayList<Recyclerview_list> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var recyclerViewAdapter: Imbuhanlist_adapter
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imbuhanlist)

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

        recyclerViewList.add(Recyclerview_list(R.drawable.ber, "Ber-"))
        recyclerViewList.add(Recyclerview_list(R.drawable.me, "Me-"))
        recyclerViewList.add(Recyclerview_list(R.drawable.di, "Di-"))
        recyclerViewList.add(Recyclerview_list(R.drawable.se, "Se-"))
        recyclerViewList.add(Recyclerview_list(R.drawable.ke, "Ke-"))
        recyclerViewList.add(Recyclerview_list(R.drawable.pe, "Pe-"))
        recyclerViewList.add(Recyclerview_list(R.drawable.ter, "Ter-"))

        recyclerViewAdapter = Imbuhanlist_adapter(recyclerViewList, this)
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
            Toast.makeText(this, "Imbuhan tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            // Sembunyikan noResultTextView dan tampilkan daftar
            noResultTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerViewAdapter.setFilteredList(filteredList)
        }
    }
}