package me.brisson.imgtotext.ui.screen.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.brisson.imgtotext.R
import me.brisson.imgtotext.ui.component.MainButton
import me.brisson.imgtotext.ui.component.MainButtonItem
import me.brisson.imgtotext.ui.theme.ImageToTextTheme

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    onCameraScan: () -> Unit,
    onImageScan: (Uri) -> Unit,
) {
    MainScreen(modifier = modifier, onCameraScan = onCameraScan, onImageScan = onImageScan)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    onCameraScan: () -> Unit,
    onImageScan: (Uri) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { onImageScan(it) } }
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(stringResource(id = R.string.app_name))
            })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {

            Icon(
                painter = painterResource(id = R.drawable.images_rafiki),
                contentDescription = null,
                tint = Color.Unspecified,
            )

            Column(
                modifier = Modifier.padding(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                MainButton(onClick = { onCameraScan() }) {
                    MainButtonItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(36.dp),
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        },
                        title = {
                            Text(
                                text = "Scan com a Câmera",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        },
                        subtitle = {
                            Text(
                                text = "Extrair texto usando a camera",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.69f),
                            )
                        }
                    )
                }
                MainButton(
                    backgroundColor = Color.Unspecified,
                    onClick = { launcher.launch("image/") },
                ) {
                    MainButtonItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(36.dp),
                                painter = painterResource(id = R.drawable.ic_image),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        },
                        title = {
                            Text(
                                text = "Escolher imagem",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        },
                        subtitle = {
                            Text(
                                text = "Extriar texto de uma imagem",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.69f),
                            )
                        }
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ImageToTextTheme {
        MainScreen(onCameraScan = { }, onImageScan = { })
    }
}
