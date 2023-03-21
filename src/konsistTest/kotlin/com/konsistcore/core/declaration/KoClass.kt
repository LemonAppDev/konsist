package com.konsistcore.core.declaration

import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.isAbstract

class KoClass(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    val isEnum by lazy { ktClass.isEnum() }

    val isSealed by lazy { ktClass.isSealed() }

    val isInner by lazy { ktClass.isInner() }

    val isValue by lazy { ktClass.isValue() }

    val isAnnotation by lazy { ktClass.isAnnotation() }

    val isData by lazy { ktClass.isData() }

    val isAbstract by lazy { ktClass.isAbstract() }

    val primaryConstructor by lazy {
        val localPrimaryConstructor = ktClass.primaryConstructor ?: return@lazy null

        KoPrimaryConstructor(
            localPrimaryConstructor,
            ktClass.primaryConstructorParameters,
            ktClass.primaryConstructorModifierList,
        )
    }

    val hasPrimaryConstructor = ktClass.hasPrimaryConstructor()

    val secondaryConstructors by lazy {
        ktClass
            .secondaryConstructors
            .map {
                KoSecondaryConstructor(it)
            }
    }

    val hasSecondaryConstructors = ktClass.hasSecondaryConstructors()

    val allConstructors = listOfNotNull(primaryConstructor) + secondaryConstructors
}
