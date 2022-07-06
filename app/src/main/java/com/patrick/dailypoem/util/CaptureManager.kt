package com.patrick.dailypoem.util

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.PixelCopy
import android.view.View
import android.view.Window
import com.patrick.dailypoem.util.Constants.TAG
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @see <a href="https://hongdroid.tistory.com/6">[안드로이드/Android] Layout 영역 사진첩 저장 + 미디어 스캔</a>
 * @see <a href="https://stackoverflow.com/a/58315279">Software rendering doesn't support hardware bitmaps</a>
 */
class CaptureManager {
    companion object {
        fun capture(view: View, activity: Activity) {
            captureView(view, activity.window) {
                saveBitmap(it)
            }
        }

        private fun saveBitmap(bitmap: Bitmap) {
            val fos: FileOutputStream

            /* 저장할 폴더 Setting */
            val uploadFolder: File =
                Environment.getExternalStoragePublicDirectory("/DCIM/DailyPoem/") // 저장 경로 (File Type형 변수)

            if (!uploadFolder.exists()) { // 만약 경로에 폴더가 없다면
                uploadFolder.mkdir() // 폴더 생성
            }
            /* 파일 저장 */
            val strPath = "${Environment.getExternalStorageDirectory().absolutePath}/DCIM/DailyPoem" // 저장 경로

            val title = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))

            try {
                fos = FileOutputStream("$strPath/$title.png")
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                Log.d(TAG, "capture: Success?")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun captureView(view: View, window: Window, bitmapCallback: (Bitmap) -> Unit) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Above Android O, use PixelCopy
                val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
                val location = IntArray(2)
                view.getLocationInWindow(location)
                PixelCopy.request(
                    window,
                    Rect(location[0], location[1], location[0] + view.width, location[1] + view.height),
                    bitmap,
                    {
                        if (it == PixelCopy.SUCCESS) {
                            bitmapCallback.invoke(bitmap)
                        }
                    },
                    Handler(Looper.getMainLooper())
                )
            } else {
                val tBitmap = Bitmap.createBitmap(
                    view.width,
                    view.height,
                    Bitmap.Config.RGB_565
                )
                val canvas = Canvas(tBitmap)
                view.draw(canvas)
                canvas.setBitmap(null)
                bitmapCallback.invoke(tBitmap)
            }
        }
    }
}
