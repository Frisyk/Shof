package com.dicoding.shof.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GamesDestailsResponse(

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description_raw")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("name")
	val name: String,
)