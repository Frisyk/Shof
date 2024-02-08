package com.dicoding.shof.ui.di

import com.dicoding.shof.core.domain.usecase.GamesInteractor
import com.dicoding.shof.core.domain.usecase.GamesUseCase
import com.dicoding.shof.ui.details.DetailsViewModel
import com.dicoding.shof.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModule = module {
    viewModel {HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}