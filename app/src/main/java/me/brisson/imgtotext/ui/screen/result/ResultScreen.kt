package me.brisson.imgtotext.ui.screen.result

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.imgtotext.ui.theme.ImageToTextTheme

@Composable
fun ResultRoute(
    modifier: Modifier = Modifier,
    viewModel: ResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ResultScreen(
        modifier = modifier,
        uiState = uiState,
        onLauncherResult = viewModel::analyzeImage,
    )

}

@Composable
internal fun ResultScreen(
    modifier: Modifier = Modifier,
    uiState: ResultUiState,
    onLauncherResult: (Context, Uri) -> Unit,
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { onLauncherResult(context, it) } }
    )

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(24.dp)) {
        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    launcher.launch("image/")
                }
            ) {
                Text("Select image")
            }

            Spacer(modifier = Modifier.padding(vertical = 12.dp))
        }

        uiState.blocks?.let { blocks ->
            items(blocks) { text ->
                SelectionContainer {
                    Text(modifier = Modifier.padding(vertical = 4.dp), text = text)
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
        ResultScreen(uiState = uiState, onLauncherResult = { _, _ -> })
    }
}