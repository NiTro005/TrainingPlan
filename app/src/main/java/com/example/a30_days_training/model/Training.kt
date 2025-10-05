package com.example.a30_days_training.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Training(
    val index: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)
