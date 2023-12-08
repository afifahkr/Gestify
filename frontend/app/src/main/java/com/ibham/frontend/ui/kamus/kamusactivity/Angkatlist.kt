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
import com.ibham.frontend.ui.kamus.adapter.Angkalist_adapter

class Angkatlist : AppCompatActivity() {

    private val recyclerViewList: ArrayList<Recyclerview_list> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var recyclerViewAdapter: Angkalist_adapter
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_angkatlist)

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

        recyclerViewList.add(Recyclerview_list(R.drawable.satu, "satu (1)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.dua, "dua (2)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.tiga, "tiga (3)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.empat, "empat (4)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.lima, "lima (5)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.enam, "enam (6)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.tujuh, "tujuh (7)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.delapan, "delapan (8)"))
        recyclerViewList.add(Recyclerview_list(R.drawable.sembilan, "sembilan (9)"))

        recyclerViewAdapter = Angkalist_adapter(recyclerViewList, this)
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
            Toast.makeText(this, "Angka tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            // Sembunyikan noResultTextView dan tampilkan daftar
            noResultTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerViewAdapter.setFilteredList(filteredList)
        }
    }
}
