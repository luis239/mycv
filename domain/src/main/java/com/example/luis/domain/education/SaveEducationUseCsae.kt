package com.example.luis.domain.education

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.education.model.EducationModel
import com.example.luis.domain.education.repository.EducationRepository
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.interactor.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SaveEducationUseCsae @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val educationRepository: EducationRepository
):CompletableUseCase<SaveEducationUseCsae.Params>(postExecutionThread) {
    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
            return educationRepository.saveInfo(params.educationModel)
    }


    data class Params(val educationModel: List<EducationModel>)
}