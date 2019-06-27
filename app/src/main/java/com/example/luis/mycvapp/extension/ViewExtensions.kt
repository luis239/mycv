package com.example.luis.mycvapp.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflater() = LayoutInflater.from(context)

fun ViewGroup.inflate(layoutRes: Int): View {
    return inflater().inflate(layoutRes, this, false)
}