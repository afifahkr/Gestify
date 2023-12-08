package com.ibham.frontend.ui

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.ibham.frontend.R

class Playvideo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playvideo)

        val videoView: VideoView = findViewById(R.id.videoView)

        // Ambil URL video dari intent
        val videoUrl = intent.getStringExtra("videoUrl")

        // Periksa apakah URL video tidak kosong
        if (!videoUrl.isNullOrEmpty()) {
            // Lokasi video, bisa berupa URL atau path lokal
            val videoUri = Uri.parse(videoUrl)

            // Atur URI video ke VideoView
            videoView.setVideoURI(videoUri)

            // Atur MediaController untuk kontrol video (play, pause, dll.)
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)

            // Mulai pemutaran video
            videoView.start()
        }
    }
}
