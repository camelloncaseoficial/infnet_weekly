package com.camelloncase.infnetweekly.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

fun showMessageToUser(context: Context?, message: String) {

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}

fun formattedCurrentDate(pattern: String): ArrayList<String> {

    val list = ArrayList<String>()
    val date = Calendar.getInstance()
    val datePlus = date.clone() as Calendar
    datePlus.add(Calendar.DAY_OF_MONTH, 7)
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())

    val formatedDate = formatter.format(date.time)
    val formatedPlusDate = formatter.format(datePlus.time)
    list.add(formatedDate)
    list.add(formatedPlusDate)

    return list
}

fun checkPasswordPattern(password: String): Boolean {

    val regex = "^(?=.*[-@!#\$%^&+=]).{6,}\$"
    val passwordPattern = Pattern.compile(regex)

    return when (password.isEmpty()) {
        true -> false
        else -> passwordPattern.matcher(password).matches()
    }
}

fun checkEmailPattern(email: String): Boolean {

    return when (email.isEmpty()) {
        true -> false
        else -> Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

private val PUNCTUATION = listOf(", ", "; ", ": ", " ")

/**
 * Truncate long text with a preference for word boundaries and without trailing punctuation.
 */
fun String.smartTruncate(length: Int): String {
    val words = split(" ")
    var added = 0
    var hasMore = false
    val builder = StringBuilder()
    for (word in words) {
        if (builder.length > length) {
            hasMore = true
            break
        }
        builder.append(word)
        builder.append(" ")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)) {
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if (hasMore) {
        builder.append("...")
    }
    return builder.toString()
}
