package com.example.catskmpdemo.feature.dashboard.presentation.cat_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catskmpdemo.common.domain.Result
import com.example.catskmpdemo.common.presentation.UiText
import com.example.catskmpdemo.feature.dashboard.domain.CatRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatListViewModel(
    private val catRepository: CatRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CatListState())
    val state: StateFlow<CatListState> = _state

    private var loadJob: Job? = null

    fun onAction(action: CatListAction) {
        when (action) {
            is CatListAction.OnScreenStarted -> {
                if (_state.value.cats.isEmpty() && !_state.value.isLoading) {
                    loadCats()
                }
            }

            is CatListAction.OnRefresh -> loadCats()
            is CatListAction.OnCatClick -> {
                // Handle navigation / selection if needed
            }
        }
    }

    private fun loadCats() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)
            when (val result = catRepository.getCats()) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        cats = result.data,
                        errorMessage = null
                    )
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = UiText.DynamicString(result.error.toString())
                    )
                }
            }
        }
    }

}