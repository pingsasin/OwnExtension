package pattararittigul.sasin.extensionlibrary.extension

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.reflect.KClass

inline val <reified T : Any> T.TAG get() = this::class.java.simpleName.toString()


fun File.toRequestBody(mediaType: String? = "image/*"): RequestBody {
    val type = MediaType.parse(mediaType!!)
    return RequestBody.create(type, this)
}

fun String.toRequestBody(mediaType: String? = "text/plain"): RequestBody {
    val type = MediaType.parse(mediaType!!)
    return RequestBody.create(type, this)
}

fun filePathToMultipartRequestBody(file: File): MultipartBody.Part {
    val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
    return MultipartBody.Part.createFormData("avatar", file.name, requestFile)
}


fun Int.toRequestBody(mediaType: String? = "text/plain"): RequestBody {
    val type = MediaType.parse(mediaType!!)
    return RequestBody.create(type, this.toString())
}
