package com.example.recyclertransition_dunets_l19

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doggo(
    val name: String,
    val age: Int,
    val imgId: Int
) : Parcelable