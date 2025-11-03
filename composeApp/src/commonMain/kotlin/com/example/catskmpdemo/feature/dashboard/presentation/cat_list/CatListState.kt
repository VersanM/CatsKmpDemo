package com.example.catskmpdemo.feature.dashboard.presentation.cat_list

import com.example.catskmpdemo.common.presentation.UiText
import com.example.catskmpdemo.feature.dashboard.domain.Cat

data class CatListState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val cats: List<Cat> = emptyList()
)
