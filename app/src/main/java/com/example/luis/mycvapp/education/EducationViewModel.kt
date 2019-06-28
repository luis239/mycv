package com.example.luis.mycvapp.education

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.luis.domain.education.GetEducationUseCase
import com.example.luis.domain.education.SaveEducationUseCsae
import com.example.luis.domain.education.model.EducationModel
import com.example.luis.domain.personalinfo.GetPersonalInfoUseCase
import com.example.luis.domain.personalinfo.SavePersonalInfoUseCsae
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.mycvapp.common.Resource
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import javax.inject.Inject

class EducationViewModel @Inject internal constructor(
    private val saveEducationUseCsae: SaveEducationUseCsae,
    private val getEducationUseCase: GetEducationUseCase
) : ViewModel() {

    init {
        getEducation()
    }

    private val response = MutableLiveData<Resource<Boolean>>()
    private val educationListResponse = MutableLiveData<Resource<List<EducationModel>>>()

    fun getResponse() : MutableLiveData<Resource<Boolean>>{
        return response
    }

    fun getEducationResponse(): MutableLiveData<Resource<List<EducationModel>>> {
        return educationListResponse
    }

    fun saveEducactionInfo(educationList:List<EducationModel>){
            saveEducationUseCsae.execute(
                object : DisposableCompletableObserver() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        response.postValue(Resource.error(e.message))
                    }

                    override fun onComplete() {
                        response.postValue(Resource.completed())
                    }

                },
                SaveEducationUseCsae.Params(educationList)
            )
    }

    private fun getEducation(){
        getEducationUseCase.execute(object : DisposableMaybeObserver<List<EducationModel>>(){
            override fun onSuccess(t: List<EducationModel>) {
                if(t.isNotEmpty())
                    educationListResponse.postValue(Resource.next(t))
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })

    }

}