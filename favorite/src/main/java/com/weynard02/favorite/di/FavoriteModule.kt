package com.weynard02.favorite.di

import com.weynard02.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}