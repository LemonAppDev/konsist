package com.lemon.konsist.ext

import com.lemon.konsist.util.KotlinFileParser
import java.io.File

internal val File.isKotlinFile get() = isFile && name.endsWith(".kt")

internal val File.isKotlinFileTxt get() = isFile && name.endsWith(".kt.txt")

fun File.toKoFile() = KotlinFileParser.getKonsistFile(this)
