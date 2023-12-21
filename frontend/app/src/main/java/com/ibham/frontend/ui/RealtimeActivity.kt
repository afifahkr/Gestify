package com.ibham.frontend.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.renderscript.Element
import android.util.Log
import android.view.Surface
import android.view.TextureView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.fitness.data.DataType
import com.ibham.frontend.R
import com.ibham.frontend.ml.HandSignDetection
import org.tensorflow.lite.schema.TensorType.FLOAT32
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class RealtimeActivity : AppCompatActivity() {

    private lateinit var labels: List<String>
    private lateinit var imageProcessor: ImageProcessor
    private lateinit var bitmap: Bitmap
    private lateinit var imageView: ImageView
    private lateinit var cameraDevice: CameraDevice
    private lateinit var handler: Handler
    private lateinit var cameraManager: CameraManager
    private lateinit var textureView: TextureView
    private lateinit var model: HandSignDetection
    private lateinit var byteBuffer: ByteBuffer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YourTag", "onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.realtime)
        getPermission()

        labels = FileUtil.loadLabels(this, "labelmap.txt")
        imageProcessor = ImageProcessor.Builder().add(ResizeOp(300, 300, ResizeOp.ResizeMethod.BILINEAR)).build()
        model = HandSignDetection.newInstance(this)
        val handlerThread = HandlerThread("videoThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        imageView = findViewById(R.id.imageView)

        textureView = findViewById(R.id.textureView)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        // Inisialisasi byteBuffer *disini salahnya mungkin
        val expectedBufferSize = 1 * 320 * 320 * 3 * 4
        byteBuffer = ByteBuffer.allocateDirect(expectedBufferSize)

        textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(p0: SurfaceTexture, p1: Int, p2: Int) {
                openCamera()
            }

            override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture, p1: Int, p2: Int) {
            }

            override fun onSurfaceTextureDestroyed(p0: SurfaceTexture): Boolean {
                return false
            }

            @SuppressLint("UnsafeExperimentalUsageError")
            override fun onSurfaceTextureUpdated(p0: SurfaceTexture) {
                Log.d("YourTag", "onSurfaceTextureUpdated() called")
                if (textureView.bitmap != null) {
                    bitmap = textureView.bitmap!!
                    var image = TensorImage.fromBitmap(bitmap)
                    image = imageProcessor.process(image)

                    // Mengisi data input model dari image ke byteBuffer
                    byteBuffer.rewind()

                    // Memastikan buffer sesuai dengan model
                    val expectedBufferSize = 1 * 320 * 320 * 3 * 4
                    if (byteBuffer.capacity() != expectedBufferSize) {
                        byteBuffer = ByteBuffer.allocateDirect(expectedBufferSize)
                    }

                    val intValues = IntArray(320 * 320)
                    image.bitmap.getPixels(intValues, 0, image.bitmap.width, 0, 0, image.bitmap.width, image.bitmap.height)

                    var pixel = 0
                    for (i in 0 until 320) {
                        for (j in 0 until 320) {
                            val value = intValues[pixel++]
                            byteBuffer.putFloat((value shr 16 and 0xFF) / 255.0f)
                            byteBuffer.putFloat((value shr 8 and 0xFF) / 255.0f)
                            byteBuffer.putFloat((value and 0xFF) / 255.0f)
                        }
                    }

                    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 320, 320, 3), org.tensorflow.lite.DataType.FLOAT32)
                    inputFeature0.loadBuffer(byteBuffer)
                    val outputs = model.process(inputFeature0)
                    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
                    val outputFeature1 = outputs.outputFeature1AsTensorBuffer
                    val outputFeature2 = outputs.outputFeature2AsTensorBuffer
                    val outputFeature3 = outputs.outputFeature3AsTensorBuffer

                    val detectionResults = processInferenceResults(outputFeature0, outputFeature1, outputFeature2, outputFeature3)
                    updateVisualization(detectionResults)
                }
            }
        }
    }

    override fun onDestroy() {
        Log.d("YourTag", "onDestroy() called")
        super.onDestroy()
        model.close()
    }

    @SuppressLint("MissingPermission")
    private fun openCamera() {
        Log.d("YourTag", "openCamera() called")
        cameraManager.openCamera(cameraManager.cameraIdList[0], object : CameraDevice.StateCallback() {
            override fun onOpened(p0: CameraDevice) {
                Log.d("YourTag", "onOpened() called")
                cameraDevice = p0

                var surfaceTexture = textureView.surfaceTexture
                var surface = Surface(surfaceTexture)

                var captureRequest = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                captureRequest.addTarget(surface)

                cameraDevice.createCaptureSession(listOf(surface), object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(p0: CameraCaptureSession) {
                        p0.setRepeatingRequest(captureRequest.build(), null, null)
                    }

                    override fun onConfigureFailed(p0: CameraCaptureSession) {
                        Log.e("YourTag", "onConfigureFailed() called")
                    }
                }, handler)
            }

            override fun onDisconnected(p0: CameraDevice) {
                Log.d("YourTag", "onDisconnected() called")
            }

            override fun onError(p0: CameraDevice, p1: Int) {
                Log.e("YourTag", "onError() called with error code $p1")
            }
        }, handler)
    }

    private fun getPermission() {
        Log.d("YourTag", "getPermission() called")
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("YourTag", "onRequestPermissionsResult() called")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            getPermission()
        }
    }

    private fun processInferenceResults(
        outputFeature0: TensorBuffer,
        outputFeature1: TensorBuffer,
        outputFeature2: TensorBuffer,
        outputFeature3: TensorBuffer
    ) {
        Log.d("YourTag", "processInferenceResults() called")
        // Proses hasil inferensi di sini jika diperlukan
    }

    private fun updateVisualization(detectionResults: Unit) {
        Log.d("YourTag", "updateVisualization() called")
        // Perbarui visualisasi di sini jika diperlukan
    }
}
