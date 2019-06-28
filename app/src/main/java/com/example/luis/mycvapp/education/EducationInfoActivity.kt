package com.example.luis.mycvapp.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.luis.domain.education.model.EducationModel
import com.example.luis.mycvapp.R
import com.example.luis.mycvapp.common.Resource
import com.example.luis.mycvapp.common.ViewModelFactory
import com.example.luis.mycvapp.dagger.Injectable
import kotlinx.android.synthetic.main.activity_education_info.*
import javax.inject.Inject

class EducationInfoActivity : AppCompatActivity(),
    EducationAdapter.OnAddCallBack,
    EducationAdapter.OnSelectedCallback,Injectable {
    private val educationArray = ArrayList<EducationModel>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var educationViewModel: EducationViewModel

    private val mAdapter = EducationAdapter(educationArray)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education_info)


        educationViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(EducationViewModel::class.java)

        educationViewModel.getEducationResponse().observe(this, Observer {
            when (it!!.status) {
                Resource.ResourceState.NEXT ->{
                    educationArray.clear()
                    educationArray.addAll(it.data!!)
                    mAdapter.updateList(educationArray)

                }
                Resource.ResourceState.ERROR -> {

                }
                Resource.ResourceState.COMPLETED -> {

                }
                else -> {
                }
            }
        })

        educationViewModel.getResponse().observe(this, Observer {
            when (it!!.status) {
                Resource.ResourceState.ERROR -> {

                }
                Resource.ResourceState.COMPLETED -> {
                    Toast.makeText(this,"Saved!",Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        })
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        educationArray.add(EducationModel())

        mAdapter.updateList(educationArray)
        recyclerView.adapter = mAdapter
        mAdapter.setAddCallback(this)
        mAdapter.setCallback(this)

        saveButton.setOnClickListener {
            educationViewModel.saveEducactionInfo(educationArray)
        }
    }

    override fun onItemSelected(position: Int) {
        educationArray.removeAt(position)
        mAdapter.updateList(educationArray)
    }

    override fun onAddItem() {
        educationArray.add(EducationModel())
        mAdapter.updateList(educationArray)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
