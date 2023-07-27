package me.brisson.imgtotext.textdetector

import android.content.Context
import android.net.Uri
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import java.io.IOException

//@ExperimentalGetImage
class ImageAnalyzer : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
//        imageProxy.image?.let { mediaImage ->
//            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
//            //Todo: Pass image to an ML Kit Vision API
//        }
    }

    fun imageFromPath(context: Context, uri: Uri): InputImage? {
        return try {
            InputImage.fromFilePath(context, uri)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}