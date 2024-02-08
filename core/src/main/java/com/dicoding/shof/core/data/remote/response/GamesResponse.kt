package com.dicoding.shof.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GamesResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("released")
	val released: String
)

