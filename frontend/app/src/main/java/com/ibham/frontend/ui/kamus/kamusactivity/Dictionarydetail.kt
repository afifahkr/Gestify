package com.ibham.frontend.ui.kamus.kamusactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ibham.frontend.R

class Dictionarydetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionarydetail)

        val pageId = intent.getIntExtra("id", 0)

        val textPageId: TextView = findViewById(R.id.textPageId)
        textPageId.text = "PAGE : $pageId"
    }
}
