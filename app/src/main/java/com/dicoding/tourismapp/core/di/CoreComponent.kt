package com.dicoding.tourismapp.core.di

import com.dicoding.tourismapp.detail.DetailTourismActivity
import com.dicoding.tourismapp.favorite.FavoriteFragment
import com.dicoding.tourismapp.home.HomeFragment
import dagger.Subcomponent

@CoreScope
@Subcomponent(
    modules = [DatabaseModule::class, NetworkModule::class, RepositoryModule::class]
)
interface CoreComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CoreComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailTourismActivity)
}