package com.dicoding.tourismapp.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.dicoding.tourismapp.core.data.TourismRepository
import com.dicoding.tourismapp.core.injection.Injection
import com.dicoding.tourismapp.detail.DetailTourismViewModel

class HomeViewModelFactory private constructor(private val tourismRepository: TourismRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: HomeViewModelFactory? = null

        fun getInstance(context: Context): HomeViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: HomeViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> { HomeViewModel(tourismRepository) as T }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
