package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.psiUtil.getStrictParentOfType
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap

internal enum class TypeKind {
    CLASS,
    INTERFACE,
    OBJECT,
    UNKNOWN,
}

internal object TypeKindUtil {
    private const val OBJECT_INSTANCE_FIELD_NAME = "INSTANCE"

    private val classpathKinds = ConcurrentHashMap<String, TypeKind>()

    /*
     * The kind of the class, interface or object. Kinds of types declared outside the project (in a library or the Kotlin
     * standard library) are read from the classpath, so they are UNKNOWN when the type is not on the classpath.
     */
    internal fun getKind(declaration: KoSourceDeclaration?): TypeKind =
        when (declaration) {
            is KoClassDeclaration -> TypeKind.CLASS
            is KoInterfaceDeclaration -> TypeKind.INTERFACE
            is KoObjectDeclaration -> TypeKind.OBJECT
            is KoExternalDeclaration -> declaration.fullyQualifiedName?.let { getClasspathKind(it) } ?: TypeKind.UNKNOWN
            is KoKotlinTypeDeclaration -> declaration.fullyQualifiedName?.let { getClasspathKind(it) } ?: TypeKind.UNKNOWN
            else -> TypeKind.UNKNOWN
        }

    /*
     * The kind of the parent. When it is not known from the parent declaration (e.g. a library missing from the classpath
     * or a type alias), it is taken from the supertype list entry.
     */
    internal fun getParentKind(
        sourceDeclaration: KoSourceDeclaration?,
        ktSuperTypeListEntry: KtSuperTypeListEntry,
    ): TypeKind =
        getKind(sourceDeclaration)
            .takeIf { it != TypeKind.UNKNOWN }
            ?: getKind(ktSuperTypeListEntry)

    /*
     * Only a class can be called (`: Foo()`) and only an interface can be delegated to (`: Foo by foo`).
     * A class can also be listed without the call (`: Foo`) when there is no primary constructor to call it from:
     * in a class with only secondary constructors and in `expect` and `external` classes.
     * Interfaces can't have class parents.
     */
    private fun getKind(ktSuperTypeListEntry: KtSuperTypeListEntry): TypeKind {
        if (ktSuperTypeListEntry is KtSuperTypeCallEntry) return TypeKind.CLASS
        if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry) return TypeKind.INTERFACE

        val ktClassOrObject = ktSuperTypeListEntry.getStrictParentOfType<KtClassOrObject>() ?: return TypeKind.UNKNOWN

        val isInterface = (ktClassOrObject as? KtClass)?.isInterface() == true
        val hasOnlySecondaryConstructors =
            ktClassOrObject.primaryConstructor == null && ktClassOrObject.secondaryConstructors.isNotEmpty()

        val canHaveClassParentWithoutCall =
            !isInterface &&
                (
                    hasOnlySecondaryConstructors ||
                        ktClassOrObject.hasModifier(KtTokens.EXPECT_KEYWORD) ||
                        ktClassOrObject.hasModifier(KtTokens.EXTERNAL_KEYWORD)
                )

        return if (canHaveClassParentWithoutCall) TypeKind.UNKNOWN else TypeKind.INTERFACE
    }

    private fun getClasspathKind(fullyQualifiedName: String): TypeKind =
        classpathKinds.computeIfAbsent(fullyQualifiedName) {
            ClasspathUtil.loadClass(it)?.let { clazz -> getKind(clazz) } ?: TypeKind.UNKNOWN
        }

    private fun getKind(clazz: Class<*>): TypeKind =
        when {
            clazz.isAnnotation -> TypeKind.CLASS
            clazz.isInterface -> TypeKind.INTERFACE
            isKotlinObject(clazz) -> TypeKind.OBJECT
            else -> TypeKind.CLASS
        }

    /*
     * The Kotlin compiler stores the instance of the object in its static `INSTANCE` field and the instance
     * of the companion object in the static field of the enclosing class named after the companion object.
     */
    private fun isKotlinObject(clazz: Class<*>): Boolean =
        clazz.isAnnotationPresent(Metadata::class.java) &&
            (
                hasStaticInstanceField(clazz, clazz, OBJECT_INSTANCE_FIELD_NAME) ||
                    clazz.enclosingClass?.let { hasStaticInstanceField(it, clazz, clazz.simpleName) } == true
            )

    private fun hasStaticInstanceField(
        owner: Class<*>,
        clazz: Class<*>,
        fieldName: String,
    ): Boolean =
        runCatching { owner.getDeclaredField(fieldName) }
            .getOrNull()
            ?.let { Modifier.isStatic(it.modifiers) && it.type == clazz } == true
}
