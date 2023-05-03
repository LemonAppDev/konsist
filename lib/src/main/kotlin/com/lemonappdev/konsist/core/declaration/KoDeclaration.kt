package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import kotlin.reflect.KClass

interface KoDeclaration : KoNamedDeclaration {
    val packageName: String

    val annotations: List<KoAnnotationDeclaration>

    val modifiers: List<KoModifier>

    val koDoc: KoDocDeclaration?

    fun hasPublicModifier(): Boolean

    fun isPublicOrDefault(): Boolean

    fun hasPrivateModifier(): Boolean

    fun hasProtectedModifier(): Boolean

    fun hasInternalModifier(): Boolean

    fun isTopLevel(): Boolean

    fun hasAnnotations(vararg names: String): Boolean

    fun hasAnnotationNameOrAnnotationFullyQualifyName(name: String): Boolean

    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean

    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    fun hasKoDoc(): Boolean

    fun resideInPackage(packageName: String): Boolean

    fun resideOutsidePackage(packageName: String): Boolean
}
