package com.example.luis.domain.education

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.education.model.EducationModel
import com.example.luis.domain.education.repository.EducationRepository
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.interactor.MaybeUseCase
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import javax.inject.Inject

class GetEducationUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val educationRepository: EducationRepository
):MaybeUseCase<List<EducationModel>,Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Maybe<List<EducationModel>> {
        return educationRepository.getEducation()
    }

}