package com.semba.githubresume.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by SeMbA on 2019-12-07.
 */

fun Date?.toFormattedString(): String
{
    if (this == null)
        return ""

    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    return formatter.format(this)
}

fun String?.toDate(): Date
{
    if (this == null)
        return Date()

    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    return formatter.parse(this) ?: Date()
}