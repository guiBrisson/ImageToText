package me.brisson.imgtotext.ui.screen.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.imgtotext.ui.theme.ImageToTextTheme

@Composable
fun ResultRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ResultScreen(
        modifier = modifier,
        uiState = uiState,
        onBack = onBack,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ResultScreen(
    modifier: Modifier = Modifier,
    uiState: ResultUiState,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Resultado") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            uiState.blocks?.let { blocks ->
                items(blocks) { text ->
                    SelectionContainer {
                        Text(modifier = Modifier.padding(vertical = 4.dp), text = text)
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE)
@Composable
private fun PreviewResultScreen() {
    ImageToTextTheme {
        val list = listOf("oi", "tudo bem?")
        val uiState = ResultUiState(blocks = list)
        ResultScreen(
            uiState = uiState,
            onBack = { },
        )
    }
}
