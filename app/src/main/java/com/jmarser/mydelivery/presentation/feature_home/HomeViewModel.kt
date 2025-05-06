package com.jmarser.mydelivery.presentation.feature_home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.domain.useCase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: HomeViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: HomeUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HomeEffect>()
    val uiEffect: SharedFlow<HomeEffect> = _uiEffect.asSharedFlow()

    private val _categoryUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Idle)
    val categoryUiState: StateFlow<CategoriesUiState> = _categoryUiState.asStateFlow()

    init {
        getAllCategories()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnCategorySelected -> categorySelected(event.category)
        }
    }

    private fun getAllCategories(){
        _categoryUiState.value = CategoriesUiState.Loading

        viewModelScope.launch {
            val result = useCase.getAllCategories()

            _categoryUiState.value = result
        }
    }

    private fun categorySelected(category: CategoryDm){
        _uiState.update { it.copy(selectedCategory = category) }

        viewModelScope.launch {
            _uiEffect.emit(HomeEffect.CategorySelected(category))
        }
    }
}