package com.lemonappdev.konsist.core.provider

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import java.awt.SystemColor.text

internal interface KoLocationProviderCore :
    KoLocationProvider {
    val psiElement: PsiElement

    override val location: String
        get() {
            val lineAndColumn = psiElement
                .getTextWithLocation()
                .substringAfterLast("' at (")
                .substringBefore(") in")
                .split(",")
                .toMutableList()
                .filterNot { it.isBlank() }

            val line = lineAndColumn[0]
            val column = lineAndColumn[1]
            return "$line:$column"
//            return "$filePath:$line:$column"
        }

    override val locationWithText: String
        get() = "Location: $location \nDeclaration:\n$text"
    // ten text jest zly
    // ToDo (change this text property - this is not this text)
}
