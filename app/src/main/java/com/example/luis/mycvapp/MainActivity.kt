package com.example.luis.mycvapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luis.cache.personalinfo.PersonalInformationEntity
import com.example.luis.cache.room.AppDb
import com.example.luis.mycvapp.dagger.Injectable
import com.example.luis.mycvapp.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createCv.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }

}
