package com.example.luis.mycvapp.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    error = errorMessage
}

fun ViewGroup.inflater() = LayoutInflater.from(context)

fun ViewGroup.inflate(layoutRes: Int): View {
    return inflater().inflate(layoutRes, this, false)
}