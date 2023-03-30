package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.Modifier
import com.lemon.konsist.ext.getKoClasses
import com.lemon.konsist.ext.getKoFunctions
import com.lemon.konsist.ext.getKoInterfaces
import com.lemon.konsist.ext.getKoObjects
import com.lemon.konsist.ext.getKoProperties
import org.jetbrains.kotlin.psi.KtClassOrObject
import kotlin.reflect.KClass

open class KoComplexDeclaration(
    private val ktClassOrObject: KtClassOrObject,
) : KoDeclaration(ktClassOrObject) {
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
            .flatMap {
                when (it) {
                    is KoComplexDeclaration -> mutableListOf(it) + it.declarations(true)
                    is KoFunction -> it.getLocalFunctions(true)
                    else -> listOf(it)
                }
            }
    }

    fun classes(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoClass>()
    } else {
        classes
    }

    fun functions(includeNested: Boolean = false) = if (includeNested) {
        functions + nestedDeclarations.filterIsInstance<KoFunction>()
    } else {
        functions
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

    fun declarations(includeNested: Boolean = false): List<KoDeclaration> = if (includeNested) {
        nestedDeclarations + functions
    } else {
        declarations
    }

    fun hasFunction(name: String, vararg modifiers: Modifier, includeNested: Boolean = false): Boolean {
        functions(includeNested).firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }

        return false
    }

    fun hasProperty(name: String, vararg modifiers: Modifier, includeNested: Boolean = false): Boolean {
        properties(includeNested).firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }
        return false
    }

    fun hasClass(name: String, vararg modifiers: Modifier, includeNested: Boolean = false): Boolean {
        classes(includeNested).firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }
        return false
    }

    fun hasInterface(name: String, vararg modifiers: Modifier, includeNested: Boolean = false): Boolean {
        interfaces(includeNested).firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }
        return false
    }

    fun hasObject(name: String, vararg modifiers: Modifier, includeNested: Boolean = false): Boolean {
        objects(includeNested).firstOrNull { it.name == name }?.let {
            return it.hasModifiers(*modifiers)
        }
        return false
    }

    fun representsType(kClass: KClass<*>) = kClass.qualifiedName == fullyQualifiedName
}
