package com.example.luis.domain.personalinfo

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.interactor.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SavePersonalInfoUseCsae @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val personalInfoRepository: PersonalInfoRepository
):CompletableUseCase<SavePersonalInfoUseCsae.Params>(postExecutionThread) {
    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
            return personalInfoRepository.saveInfo(params.personalInformationModel)
    }


    data class Params(val personalInformationModel: PersonalInformationModel)
}