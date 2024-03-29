package com.example.luis.mycvapp.personalinfo.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.luis.domain.personalinfo.GetPersonalInfoUseCase
import com.example.luis.domain.personalinfo.SavePersonalInfoUseCsae
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.mycvapp.common.Resource
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import javax.inject.Inject

class PersonalInfoViewModel @Inject internal constructor(
    private val savePersonalInfoUseCsae: SavePersonalInfoUseCsae,
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase
) : ViewModel() {

    var name = MutableLiveData<String>()
    var nameError = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var phoneError = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var emailError = MutableLiveData<String>()
    var birthday = MutableLiveData<String>()
    var birthdayError = MutableLiveData<String>()

    var pictureImageUri = MutableLiveData<String>()

    init {
        getPersonalInfo()
    }

    private val response = MutableLiveData<Resource<Boolean>>()

    fun getResponse() : MutableLiveData<Resource<Boolean>>{
        return response
    }

    fun isValidFields(): Boolean {
        var isValid = true

        if (TextUtils.isEmpty(email.value)) {
            emailError.value = "is Requiered"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailError.value = "Invalid email"
            isValid = false
        } else {
            emailError.value = ""
        }
        
        if (TextUtils.isEmpty(phone.value)) {
            phoneError.value = "is Requiered"
            isValid = false
        } else {
            phoneError.value = ""
        }

        if (TextUtils.isEmpty(name.value)) {
            nameError.value = "is Requiered"
            isValid = false
        } else {
            nameError.value = ""
        }

        if (TextUtils.isEmpty(birthday.value)) {
            birthdayError.value = "is Requiered"
            isValid = false
        } else {
            birthdayError.value = ""
        }

        return isValid
    }

    fun savePersonalInfo(uri:String){
        if(isValidFields()) {
            savePersonalInfoUseCsae.execute(
                object : DisposableCompletableObserver() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        response.postValue(Resource.error(e.message))
                    }

                    override fun onComplete() {
                        response.postValue(Resource.completed())
                    }

                },
                SavePersonalInfoUseCsae.Params(
                    PersonalInformationModel(
                        "1",
                        name.value!!,
                        email.value!!,
                        phone.value!!,
                        uri,
                        birthday.value!!
                    )
                )
            )
        }
    }

    private fun getPersonalInfo(){
        getPersonalInfoUseCase.execute(object : DisposableMaybeObserver<PersonalInformationModel>(){
            override fun onSuccess(t: PersonalInformationModel) {
                name.value = t.name
                phone.value = t.phone
                email.value = t.email
                birthday.value = t.birthday
                pictureImageUri.value = t.image
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

        })

    }

}