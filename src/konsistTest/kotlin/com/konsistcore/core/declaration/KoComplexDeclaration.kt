package com.konsistcore.core.declaration

import com.konsistcore.core.const.Modifier
import com.konsistcore.ext.getKoClasses
import com.konsistcore.ext.getKoFunctions
import com.konsistcore.ext.getKoInterfaces
import com.konsistcore.ext.getKoObjects
import com.konsistcore.ext.getKoProperties
import com.mango.MangoApplication
import org.jetbrains.kotlin.psi.KtClassOrObject
import kotlin.reflect.KClass

open class KoComplexDeclaration(private val ktClassOrObject: KtClassOrObject) : KoDeclaration(ktClassOrObject) {
    private val classes by lazy {
        ktClassOrObject.getKoClasses()
    }

    private val interfaces by lazy {
        ktClassOrObject.getKoInterfaces()
    }

    private val objects by lazy {
        ktClassOrObject.getKoObjects()
    }

    private val properties by lazy {
        ktClassOrObject.getKoProperties()
    }

    private val functions by lazy {
        ktClassOrObject.getKoFunctions()
    }

    private val declarations by lazy {
        listOf(classes, interfaces, objects, properties, functions).flatten()
    }

    private val nestedDeclarations by lazy {
        declarations
            .filterIsInstance<KoComplexDeclaration>()
            .flatMap { mutableListOf(it) + it.declarations(true) }
    }

    fun getClasses(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoClass>()
    } else {
        classes
    }

    fun interfaces(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoInterface>()
    } else {
        interfaces
    }

    fun objects(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoObject>()
    } else {
        objects
    }

    fun properties(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoProperty>()
    } else {
        properties
    }

    fun localFunctions(includeNested: Boolean = false) = if (includeNested) {
        declarations
            .filterIsInstance<KoComplexDeclaration>()
            .flatMap { it.declarations(true) }
            .filterIsInstance<KoFunction>()
    } else {
        functions
    }

    fun declarations(includeNested: Boolean = false): List<KoDeclaration> = if (includeNested) {
        nestedDeclarations + properties + functions
    } else {
        declarations
    }

    fun hasFunction(name: String, vararg modifiers: Modifier): Boolean {
        functions.firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }

        return false
    }

    fun hasProperty(name: String, vararg modifiers: Modifier): Boolean {
        properties.firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }
        return false
    }

    fun representsType(kClass: KClass<MangoApplication>) = kClass.qualifiedName == fullyQualifiedName
}
