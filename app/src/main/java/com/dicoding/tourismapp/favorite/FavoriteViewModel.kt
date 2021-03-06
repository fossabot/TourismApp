package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.data.TourismRepository

class FavoriteViewModel(private val tourismRepository: TourismRepository) : ViewModel() {
    fun getFavoriteTourism() = tourismRepository.getFavoriteTourism()
}

