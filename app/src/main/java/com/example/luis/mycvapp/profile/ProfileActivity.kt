package com.example.luis.mycvapp.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luis.mycvapp.R
import com.example.luis.mycvapp.education.EducationInfoActivity
import com.example.luis.mycvapp.model.ItemProfile
import com.example.luis.mycvapp.personalinfo.PersonalInfoActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfileAdapter.OnSelectedCallback {


    override fun onItemSelected(item: ItemProfile) {
        when (item){
            ItemProfile.PERSONAL_INFORMATION -> startActivity(Intent(this,PersonalInfoActivity::class.java))
            ItemProfile.EDUCATION -> startActivity(Intent(this,EducationInfoActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val mAdapter = ProfileAdapter()
        val menu = mutableListOf<ItemProfile>()
        menu.add(ItemProfile.PERSONAL_INFORMATION)
        menu.add(ItemProfile.EDUCATION)
        mAdapter.updateList(menu)
        recyclerProfile.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
        mAdapter.setCallback(this)

    }
}
