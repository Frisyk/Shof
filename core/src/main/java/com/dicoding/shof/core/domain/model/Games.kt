package com.dicoding.shof.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val id: Int,
    val name: String?,
    val released: String?,
    val rating: Double?,
    val imageBackground: String?,
    var isFavorite: Boolean = false
) : Parcelable

data class GameDetails(
    val id: Int,
    val name: String,
    val rating: Double,
    val description: String,
    val released: String,
    val website: String,
    val backgroundImageAdditional: String,
    val backgroundImage: String,
    val tba: Boolean
)
