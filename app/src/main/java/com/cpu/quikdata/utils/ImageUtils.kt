package com.cpu.quikdata.utils

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import java.io.*
import java.util.*

/*
 * Based on https://gist.github.com/ZeroBrain/b93a1cbd889b672e8b06
 */

fun getImageUri(context: Context, uri: Uri) : Uri {
    if ("content" == uri.scheme!!.toLowerCase()) {
        when {
            isGoogleOldPhotosUri(uri) -> // return http path, then download file.
                //            return uri.lastPathSegment
                return uri
            isGoogleNewPhotosUri(uri) -> // copy from uri. context.getContentResolver().openInputStream(uri);
                return copyFile(context, uri)
            isPicasaPhotoUri(uri) -> // copy from uri. context.getContentResolver().openInputStream(uri);
                return copyFile(context, uri)
        }
    }
    return uri
}

private fun isGoogleOldPhotosUri(uri: Uri) : Boolean {
    return "com.google.android.apps.photos.content" == uri.authority
}

private fun isGoogleNewPhotosUri(uri: Uri) : Boolean {
    return "com.google.android.apps.photos.contentprovider" == uri.authority
}

private fun isPicasaPhotoUri(uri: Uri) : Boolean {

    return !TextUtils.isEmpty(uri.authority) && (uri.authority!!.startsWith("com.android.gallery3d")
            || uri.authority!!.startsWith("com.google.android.gallery3d"))
}

private fun copyFile(context: Context, uri: Uri): Uri {

    var filePath: String
    var inputStream: InputStream? = null
    var outStream: BufferedOutputStream? = null
    try {
        inputStream = context.contentResolver.openInputStream(uri)

        val extDir = context.getExternalFilesDir(null)
        filePath = extDir!!.absolutePath + "/IMG_" + UUID.randomUUID().toString() + ".jpg"
        outStream = BufferedOutputStream(FileOutputStream(filePath))

        val buf = ByteArray(2048)
        var len = inputStream.read(buf)
        while (len > 0) {
            outStream.write(buf, 0, len)
            len = inputStream.read(buf)
        }


    } catch (e: IOException) {
        e.printStackTrace()
        filePath = ""
    } finally {
        try {
            if (inputStream != null) {
                inputStream.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            if (outStream != null) {
                outStream.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    return Uri.fromFile(File(filePath))
}