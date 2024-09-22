package com.weynard02.capstonegamelistapp.di

import com.weynard02.capstonegamelistapp.detail.DetailViewModel
import com.weynard02.capstonegamelistapp.home.HomeViewModel
import com.weynard02.core.domain.usecase.GameInteractor
import com.weynard02.core.domain.usecase.GameUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> {
        GameInteractor(get())
    }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
}
