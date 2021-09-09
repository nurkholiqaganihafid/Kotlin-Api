package com.example.kotlinapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide


//import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Mengganti judul langsung berubah dengan nama hero
        supportActionBar!!.title = intent.getStringExtra("intent_title")

        //mengambil data gambar
//        Glide.with(this)
//            .load(intent.getStringExtra("intent_image"))
//            .placeholder(R.drawable.img_placeholder)
//            .error((R.drawable.img_placeholder))
//                //ketika pake android extensions
//            .into(imageViewHero)

        Glide.with(this)
            .load(intent.getStringExtra("intent_image"))
            .placeholder(R.drawable.img_placeholder)
            .error((R.drawable.img_placeholder))
            //ketika tdk pake android extensions
            .into(findViewById(R.id.imageViewHero))

    }
}