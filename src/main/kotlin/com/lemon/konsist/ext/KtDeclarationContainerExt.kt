package com.lemon.konsist.ext

import org.jetbrains.kotlin.psi.KtDeclarationContainer

fun KtDeclarationContainer.getKoClasses() = declarations.mapToKoClass()

fun KtDeclarationContainer.getKoInterfaces() = declarations.mapToKoInterface()

fun KtDeclarationContainer.getKoObjects() = declarations.mapToKoObject()

fun KtDeclarationContainer.getKoProperties() = declarations.mapToKoProperty()

fun KtDeclarationContainer.getKoFunctions() = declarations.mapToKoFunction()
