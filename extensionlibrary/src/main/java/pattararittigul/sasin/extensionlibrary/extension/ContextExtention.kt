package pattararittigul.sasin.extensionlibrary.extension

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

fun Context.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)

inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = intent<T>()
    intent.body()
    return intent
}

inline fun <reified T : AppCompatActivity> Context?.startActivity() = this?.startActivity(intent<T>())

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Context.getColorRes(colorId: Int): String {
    return try {
        resources.getString(colorId)
    } catch (e: Resources.NotFoundException) {
        "#ffffff"
    }
}

fun Context.toPlayStore(appPackageName: String) {
    try {
        val marketUrl = "market://details?id=$appPackageName"
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(marketUrl)))
    } catch (e: android.content.ActivityNotFoundException) {
        val googlePlayUrl = "https://play.google.com/store/apps/details?id=$appPackageName"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(googlePlayUrl)))
    }
}
