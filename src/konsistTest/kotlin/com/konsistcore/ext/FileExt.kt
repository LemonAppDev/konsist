package com.konsistcore.ext

import com.konsistcore.util.KotlinFileParser
import java.io.File

internal val File.isKotlinFile get() = isFile && name.endsWith(".kt")

fun File.toKoFile() = KotlinFileParser.getKonsistFile(this)
