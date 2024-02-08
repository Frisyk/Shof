package com.dicoding.shof.favorites.favorites.di

import com.dicoding.shof.favorites.favorites.FavoritesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoritesViewModel(get()) }
}