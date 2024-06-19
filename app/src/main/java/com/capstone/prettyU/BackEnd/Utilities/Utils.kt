package com.capstone.prettyU.BackEnd.Utilities

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.capstone.prettyU.BuildConfig
import com.capstone.prettyU.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utils {
    private val fileNameFormat = "yyyyMMdd_HHmmss"
    private val timeStamp: String = SimpleDateFormat(fileNameFormat, Locale.US).format(Date())

    fun File.reduceFileImage(): File {
        val MAX_FILE_SIZE = 1000000
        val file = this
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAX_FILE_SIZE)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }


    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timeStamp, ".jpg", filesDir)
    }

    fun uriToFile(imageUri: Uri, context: Context): File {
        val myFile = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
        outputStream.close()
        inputStream.close()
        return myFile
    }

    // Geocoder untuk mendapatkan lokasi berdasarkan longitude, latitude
    fun getAddressFromMap(context: Context, lat: Double, lon: Double): String? {
        var title: String? = "Geocoder is not available for this coordinate (lon:$lon lat:$lat)"
        val geocoder = Geocoder(context)
        val addresses = geocoder.getFromLocation(lat, lon, 1)
        title = if (!addresses.isNullOrEmpty()) {
            val address = addresses[0].getAddressLine(0)
            "$address"
        } else {
            "Unknown Area"
        }

        return title
    }

    fun getImageUri(context: Context): Uri {
        var uri: Uri? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp.jpg")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MyCamera/")
            }
            uri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
        }
        return uri ?: getImageUriForPreQ(context)
    }

    private fun getImageUriForPreQ(context: Context): Uri {
        val filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(filesDir, "/MyCamera/$timeStamp.jpg")
        if (imageFile.parentFile?.exists() == false) imageFile.parentFile?.mkdir()
        return FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.fileprovider",
            imageFile
        )
    }

    fun intentDialogBuilder(
        context: Context,
        title: String,
        message: String,
        moveToNextIntent: Boolean,
        nextIntentClass: Class<*>
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("OK") { dialog, _ ->
            if (moveToNextIntent) {
                context.startActivity(Intent(context, nextIntentClass))
            } else {
                dialog.dismiss()
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun setTipsContent(context: Context, reqCode : String): Array<String> {
        val resources = context.resources
        var title = "KOSONG"
        var desc1 = "KOSONG"
        var desc2 = "KOSONG"
        //val universities = arrayListOf<String>(*)

        fun content1 () {
            title =  resources.getStringArray(R.array.article_dry_1)[0]
            desc1 =  resources.getStringArray(R.array.article_dry_1)[1]
            desc2 =  resources.getStringArray(R.array.article_dry_1)[2]
            //title =
        }
        fun content2 () {
            title =  resources.getStringArray(R.array.article_dry_2)[0]
            desc1 =  resources.getStringArray(R.array.article_dry_2)[1]
            desc2 =  resources.getStringArray(R.array.article_dry_2)[2]
            //title =
        }
        fun content3 () {
            title =  resources.getStringArray(R.array.article_dry_3)[0]
            desc1 =  resources.getStringArray(R.array.article_dry_3)[1]
            desc2 =  resources.getStringArray(R.array.article_dry_3)[2]
            //title =
        }

        fun outOfBound() {
            title = "mbuh error"
            desc1 = "mbuh error"
            desc2 = "mbuh error"
        }

        when (reqCode) {
            "1" -> content1()
            "2" -> content1()
            "3" -> content1()
            else -> {
                outOfBound()
            }
        }
        return arrayOf(title, desc1, desc2)
    }

}