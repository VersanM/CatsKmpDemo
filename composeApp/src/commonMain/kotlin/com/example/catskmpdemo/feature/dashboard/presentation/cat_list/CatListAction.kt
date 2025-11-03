package com.example.catskmpdemo.feature.dashboard.presentation.cat_list

import com.example.catskmpdemo.feature.dashboard.domain.Cat

sealed interface CatListAction {
    data class OnCatClick(val cat: Cat) : CatListAction
    data object OnRefresh : CatListAction
    data object OnScreenStarted : CatListAction
}