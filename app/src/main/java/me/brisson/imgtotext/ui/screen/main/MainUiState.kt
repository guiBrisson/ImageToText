package me.brisson.imgtotext.ui.screen.main

data class MainUiState(
    val blocks: List<String>? = null,
    val loadingBlocks: Boolean = false,
)
