package me.brisson.imgtotext.ui.screen.result

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.imgtotext.textdetector.ImageAnalyzer
import me.brisson.imgtotext.textdetector.TextRecognition
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    fun analyzeImage(context: Context, uri: Uri) {
        _uiState.update { it.copy(loadingBlocks = true) }
        ImageAnalyzer().imageFromPath(context, uri)?.let { imageInput ->
            recognizeText(imageInput)
        }
    }

    private fun recognizeText(image: InputImage) {
        viewModelScope.launch {
            TextRecognition.recognizeText(image)?.also { textBlocks ->
                val blocks = textBlocks.map { it.text }
                _uiState.update { it.copy(blocks = blocks, loadingBlocks = false) }
            }
        }
    }
}
