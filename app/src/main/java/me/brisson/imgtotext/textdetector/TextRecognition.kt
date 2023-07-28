package me.brisson.imgtotext.textdetector

import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text.TextBlock
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object TextRecognition {
    private val TAG = TextRecognition::class.simpleName

    suspend fun recognizeText(image: InputImage): List<TextBlock>? = suspendCoroutine { cont ->
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                Log.d(TAG, "recognizeText: $visionText")
                recognizer.close()
                cont.resume(visionText.textBlocks)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "recognizeText: $e")
                recognizer.close()
                cont.resumeWithException(e)
            }
    }
}
