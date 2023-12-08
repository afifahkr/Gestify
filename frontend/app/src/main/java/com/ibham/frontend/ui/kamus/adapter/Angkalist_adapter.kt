// Recyclerview_adapter.kt
package com.ibham.frontend.ui.kamus.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ibham.frontend.R
import com.ibham.frontend.ui.playvideo
import com.ibham.frontend.ui.kamus.kamusactivity.Recyclerview_list

class Angkalist_adapter(private var itemList: List<Recyclerview_list>, private val context: Context) :
    RecyclerView.Adapter<Angkalist_adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val itemNameTextView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dictionaryview_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.itemNameTextView.text = currentItem.getItemName()

        holder.cardView.setOnClickListener {
            // Format nomor posisi dengan angka nol di depan jika kurang dari 10
            val formattedPosition = String.format("%02d", position)

            // Tentukan URL video berdasarkan posisi item yang diklik
            val videoUrl = when (position) {
                0 -> "https://pmpk.kemdikbud.go.id/sibi/SIBI/angka/01.webm"
                else -> "https://pmpk.kemdikbud.go.id/sibi/SIBI/angka/$formattedPosition.webm"
            }

            // Mulai aktivitas untuk memutar video
            val intent = Intent(context, playvideo::class.java)
            intent.putExtra("videoUrl", videoUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setFilteredList(filteredList: List<Recyclerview_list>) {
        itemList = filteredList
        notifyDataSetChanged()
    }
}
