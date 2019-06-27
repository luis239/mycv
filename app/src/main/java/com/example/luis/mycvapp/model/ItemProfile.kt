package com.example.luis.mycvapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.luis.mycvapp.R

enum class ItemProfile constructor(
    @DrawableRes val iconId: Int,
    @StringRes val resLabel: Int
){

    PERSONAL_INFORMATION(R.drawable.ic_round_create_24px,R.string.personal_info),
    EDUCATION(R.drawable.ic_round_create_24px,R.string.education),
    EXPERIENCE(R.drawable.ic_round_create_24px,R.string.experience),
    SKILLS(R.drawable.ic_round_create_24px,R.string.skills),
    COURSES(R.drawable.ic_round_create_24px,R.string.courses)
}