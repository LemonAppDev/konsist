package com.lemon.konsist.ext

import com.lemon.konsist.util.KotlinFileParser
import java.io.File

internal val File.isKotlinFile get() = isFile && name.endsWith(".kt")

fun File.toKoFile() = KotlinFileParser.getKonsistFile(this)
