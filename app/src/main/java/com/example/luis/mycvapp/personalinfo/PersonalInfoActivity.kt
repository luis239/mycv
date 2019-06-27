package com.example.luis.mycvapp.personalinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.luis.mycvapp.R
import com.example.luis.mycvapp.common.ViewModelFactory
import com.example.luis.mycvapp.dagger.Injectable
import com.example.luis.mycvapp.databinding.ActivityPersonalInfoBinding
import com.example.luis.mycvapp.personalinfo.viewmodel.PersonalInfoViewModel
import javax.inject.Inject

class PersonalInfoActivity : AppCompatActivity(),Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var personalInfoViewModel: PersonalInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityPersonalInfoBinding>(this,R.layout.activity_personal_info)


        personalInfoViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PersonalInfoViewModel::class.java)

        binding.activity = this
        binding.vm = personalInfoViewModel
        //binding.setLifecycleOwner(this)

       /* personalInfoViewModel.getResponse().observe(this, Observer {

        })*/
    }

    fun saveAction(){
        personalInfoViewModel.savePersonalInfo()
    }
}
