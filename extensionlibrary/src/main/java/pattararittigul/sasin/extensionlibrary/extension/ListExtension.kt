package pattararittigul.sasin.extensionlibrary.extension

fun <T> List<T>.isNotEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

inline fun <T> List<T>.ifNotEmpty(block: (List<T>) -> List<T>): List<T>? {
    return if (!isEmpty()) block(this) else null
}

inline fun <T, R> Collection<T>?.ifNotEmpty(block: (List<T>) -> R) {
    val list = this?.takeIf { !it.isEmpty() }
    list?.let {
        block(ArrayList(it))
    }
}