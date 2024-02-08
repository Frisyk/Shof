package com.dicoding.shof.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.shof.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(private val gamesUseCase: GamesUseCase) : ViewModel() {

    val query = MutableStateFlow<String?>("")

    val searchResult = query
        .debounce(300)
        .distinctUntilChanged()
        .map { query ->
            if (query.isNullOrBlank()) {
                return@map null
            } else {
                return@map query.trim()
            }
        }
        .flatMapLatest { sQuery ->
            gamesUseCase.getAllGames(sQuery)
        }
        .asLiveData()


}