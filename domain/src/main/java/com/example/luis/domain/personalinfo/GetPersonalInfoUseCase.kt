package com.example.luis.domain.personalinfo

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.interactor.MaybeUseCase
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import javax.inject.Inject

class GetPersonalInfoUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val personalInfoRepository: PersonalInfoRepository
):MaybeUseCase<PersonalInformationModel,Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Maybe<PersonalInformationModel> {
        return personalInfoRepository.getPersonalInfo()
    }

}