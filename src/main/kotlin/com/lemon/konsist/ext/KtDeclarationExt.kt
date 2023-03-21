package com.lemon.konsist.ext

import com.lemon.konsist.core.declaration.KoClass
import com.lemon.konsist.core.declaration.KoFunction
import com.lemon.konsist.core.declaration.KoInterface
import com.lemon.konsist.core.declaration.KoObject
import com.lemon.konsist.core.declaration.KoProperty
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtProperty

fun <E : KtDeclaration> List<E>.mapToKoClass() = filterIsInstance<KtClass>()
    .filter { !it.isInterface() }
    .map { KoClass(it) }

fun <E : KtDeclaration> List<E>.mapToKoInterface() = filterIsInstance<KtClass>()
    .filter { it.isInterface() }
    .map { KoInterface(it) }

fun <E : KtDeclaration> List<E>.mapToKoObject() = filterIsInstance<KtObjectDeclaration>()
    .map { KoObject(it) }

fun <E : KtDeclaration> List<E>.mapToKoProperty() = filterIsInstance<KtProperty>()
    .map { KoProperty(it) }

fun <E : KtDeclaration> List<E>.mapToKoFunction() = filterIsInstance<KtFunction>()
    .map { KoFunction(it) }
