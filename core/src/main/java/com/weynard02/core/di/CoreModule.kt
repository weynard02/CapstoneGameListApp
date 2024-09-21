package com.weynard02.core.di

import com.weynard02.core.BuildConfig
import androidx.room.Room
import com.weynard02.core.data.GameRepository
import com.weynard02.core.data.source.local.LocalDataSource
import com.weynard02.core.data.source.local.room.GameDatabase
import com.weynard02.core.data.source.remote.RemoteDataSource
import com.weynard02.core.data.source.remote.network.ApiService
import com.weynard02.core.domain.repository.IGameRepository
import com.weynard02.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory {
        get<GameDatabase>().gameDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            ))
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<IGameRepository> {
        GameRepository(
            get(),
            get(),
            get()
        )
    }
}