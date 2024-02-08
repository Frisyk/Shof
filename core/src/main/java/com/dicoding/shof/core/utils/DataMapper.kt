package com.dicoding.shof.core.utils


import com.dicoding.shof.core.data.local.entity.GamesEntity
import com.dicoding.shof.core.data.remote.response.GamesDestailsResponse
import com.dicoding.shof.core.data.remote.response.ResultsItem
import com.dicoding.shof.core.domain.model.GameDetails
import com.dicoding.shof.core.domain.model.Games

object DataMapper {

    fun mapResponseToDomain(input: ResultsItem): Games =
        Games(
            id = input.id,
            name = input.name,
            imageBackground = input.backgroundImage,
            released = input.released,
            rating = input.rating,
            isFavorite = false
        )

    fun mapDetailsResponseToDomain(response: GamesDestailsResponse): GameDetails {
        return GameDetails(
            id = response.id,
            name = response.name,
            rating = response.rating,
            description = response.description,
            released = response.released,
            website = response.website,
            backgroundImageAdditional = response.backgroundImageAdditional,
            backgroundImage = response.backgroundImage,
            tba = response.tba
        )
    }
    fun mapEntitiesToDomain(input: List<GamesEntity>): List<Games> =
        input.map {
            Games(
                id = it.id,
                name = it.name,
                imageBackground = it.imageBackground,
                released = it.released,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Games) = GamesEntity(
        id = input.id,
        imageBackground = input.imageBackground,
        name = input.name,
        released = input.released,
        rating = input.rating,
        isFavorite = input.isFavorite
    )
}