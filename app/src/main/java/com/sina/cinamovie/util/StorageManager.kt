package com.sina.cinamovie.util

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.provider.OpenableColumns
import androidx.core.content.ContextCompat
import timber.log.Timber
import java.io.*

class StorageManager @JvmOverloads constructor(val context: Context) {

    fun getPrimaryExternalStorage(): File {
        val externalStorageVolumes: Array<out File> = ContextCompat.getExternalFilesDirs(context, null)
        return externalStorageVolumes[0]
    }

    fun externalStorageAvailable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun getAvailableInternalMemorySize(): Long {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            stat.blockSizeLong
        } else {
            stat.blockSize.toLong()
        }
        val availableBlocks = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            stat.availableBlocksLong
        } else {
            stat.availableBlocks.toLong()
        }
        return availableBlocks * blockSize
    }

    fun getTotalInternalMemorySize(): Long {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            stat.blockSizeLong
        } else {
            stat.blockSize.toLong()
        }
        val totalBlocks = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            stat.blockCountLong
        } else {
            stat.blockCount.toLong()
        }
        return totalBlocks * blockSize
    }

    fun getAvailableExternalMemorySize(): Long {
        return if (externalStorageAvailable()) {
            val path = getPrimaryExternalStorage()
            val stat = StatFs(path.path)
            val blockSize = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                stat.blockSizeLong
            } else {
                stat.blockSize.toLong()
            }
            val availableBlocks = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                stat.availableBlocksLong
            } else {
                stat.availableBlocks.toLong()
            }
            availableBlocks * blockSize
        } else {
            -1
        }
    }

    fun getTotalExternalMemorySize(): Long {
        return if (externalStorageAvailable()) {
            val path = getPrimaryExternalStorage()
            val stat = StatFs(path.path)
            val blockSize = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                stat.blockSizeLong
            } else {
                stat.blockSize.toLong()
            }
            val totalBlocks = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                stat.blockCountLong
            } else {
                stat.blockCount.toLong()
            }
            totalBlocks * blockSize
        } else {
            -1
        }
    }

    fun getPictureDir(isInternal: Boolean = true , pathAddress: String = ""): File? {
        return if (isInternal) {
            if (pathAddress.trim() == "") {
                context.getDir(Environment.DIRECTORY_PICTURES , Context.MODE_PRIVATE)
            }
            else {
                val file = File(context.getDir(pathAddress , Context.MODE_PRIVATE).absolutePath + File.separator + Environment.DIRECTORY_PICTURES)
                if (file.mkdirs()) {
                    Timber.d("FileCreated")
                }
                file
            }
        } else {
            if (externalStorageAvailable()) {
                if (pathAddress.trim() == "") {
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                }
                else {
                    val file = File(context.getExternalFilesDir(pathAddress)?.absolutePath + File.separator + Environment.DIRECTORY_PICTURES)
                    if (file.mkdirs()) {
                        Timber.d("FileCreated")
                    }
                    file
                }
            }
            else null
        }
    }

    fun getPictureFile(fileName: String , pathAddress: String = ""): File = File(getPictureDir(pathAddress = pathAddress) , fileName)

    fun checkFileAvailability(file: File): Boolean = file.exists()

    fun deleteFile(file: File): Boolean = file.delete()

    @SuppressLint("Range")
    fun copyFile(uri: Uri): File {
        var displayName = "image.jpg"
        val contentResolver: ContentResolver = context.contentResolver
        var destFile = File(uri.toString())
        try {
            contentResolver.query(uri, null, null, null, null).use { cursor ->
                if (cursor != null && cursor.moveToFirst()) {
                    displayName =
                        cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }

                destFile = getPictureFile(displayName , DOWNLOAD_FILES_DIR)

                val outStream = FileOutputStream(destFile)
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                val buffer = ByteArray(1024)
                var read: Int
                while (inputStream?.read(buffer).also { read = it!! } != -1) {
                    (outStream as OutputStream).write(buffer, 0, read)
                }
                inputStream?.close()
                outStream.flush()
                (outStream as OutputStream).close()
                Timber.d("File Import Complete")
                return destFile
            }
        } catch (e: IOException) {
            Timber.d("File Import FAILED %s", e.toString())
            e.printStackTrace()
            return destFile;
        }
    }

}