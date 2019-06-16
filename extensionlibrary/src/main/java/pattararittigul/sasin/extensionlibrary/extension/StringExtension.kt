package pattararittigul.sasin.extensionlibrary.extension

import android.content.Context
import androidx.annotation.RawRes
import androidx.core.text.isDigitsOnly
import com.google.gson.Gson
import java.io.FileNotFoundException
import java.io.InputStream


inline fun <T> String.ifNotEmpty(block: (String) -> T): T? = if (!isEmpty()) block(this) else null

inline fun <R> ifNotEmpty(first: String?, block: (String) -> R) {
    first?.ifNotEmpty {
        block(first)
    }
}

inline fun <R> ifNotEmpty(first: String?, second: String?, block: (String, String) -> R): R? {
    first?.ifNotEmpty {
        ifNotEmpty(second) { _2 ->
            return block(it, _2)
        }
    }

    return null
}

inline fun <R> ifNotEmpty(first: String?, second: String?, third: String?, block: (String, String, String) -> R): R? {
    first?.ifNotEmpty {
        ifNotEmpty(second, third) { _2, _3 ->
            return block(it, _2, _3)
        }
    }

    return null
}

inline fun <T, V, R> with(first: T?, second: V?, block: (T, V) -> R): R? {
    first?.let { t ->
        second?.let { v ->
            return block(t, v)
        }
    }
    return null
}


fun String.subStringToListOfCharWithUpperCase(): MutableList<String> = this.toUpperCase().subStringToListOfChar()

fun String.subStringToListOfChar(): MutableList<String> =
    this.toCharArray().flatMapTo(mutableListOf()) { char -> listOf(char.toString()) }

@Throws(FileNotFoundException::class)
inline fun <reified T> Context.readJsonAsObject(@RawRes rawId: Int): T {
    val inputStream: InputStream = resources.openRawResource(rawId)
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return Gson().fromJson<T>(inputString, T::class.java)
}

@Throws(FileNotFoundException::class)
inline fun <reified T> readJsonAsObject(rawJson: String): T {
    return Gson().fromJson<T>(rawJson, T::class.java)
}

fun String.splitAsIntArray(delimiter: String = "."): IntArray {
    val stringArray = this.split(delimiter)
    val partList = mutableListOf<Int>()
    for (string in stringArray) {
        if (string.isDigitsOnly()) {
        partList.add( string.toInt())
    }}
    return partList.toIntArray()
}