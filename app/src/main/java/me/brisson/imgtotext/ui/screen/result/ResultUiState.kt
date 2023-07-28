package me.brisson.imgtotext.ui.screen.result

data class ResultUiState(
    val blocks: List<String>? = null,
    val loadingBlocks: Boolean = false,
)
