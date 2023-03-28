package com.lemon.konsist.core.declaration

import com.lemon.konsist.ext.getKoClasses
import com.lemon.konsist.ext.getKoFunctions
import com.lemon.konsist.ext.getKoInterfaces
import com.lemon.konsist.ext.getKoObjects
import com.lemon.konsist.ext.getKoProperties
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

class KoFile(private val ktFile: KtFile) : KoNamedDeclaration(ktFile) {
    val imports by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImport(it) }
    }

    val packageDirective by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackage(it) }
        }
    }

    private val classes by lazy {
        ktFile.getKoClasses()
    }

    private val interfaces by lazy {
        ktFile.getKoInterfaces()
    }

    private val objects by lazy {
        ktFile.getKoObjects()
    }

    private val properties by lazy {
        ktFile.getKoProperties()
    }

    private val functions by lazy {
        ktFile.getKoFunctions()
    }

    private val declarations by lazy {
        listOf(classes, interfaces, objects, properties, functions).flatten()
    }

    private val nestedDeclarations by lazy {
        declarations
            .flatMap {
                when (it) {
                    is KoComplexDeclaration -> {
                        it.declarations(true)
                    }

                    is KoFunction -> {
                        listOf(it) + it.getLocalFunctions(true)
                    }

                    else -> {
                        listOf(it)
                    }
                }
            }
    }

    fun classes(includeNested: Boolean = false) = if (includeNested) {
        classes + nestedDeclarations.filterIsInstance<KoClass>()
    } else {
        classes
    }

    fun interfaces(includeNested: Boolean = false) = if (includeNested) {
        interfaces + nestedDeclarations.filterIsInstance<KoInterface>()
    } else {
        interfaces
    }

    fun objects(includeNested: Boolean = false) = if (includeNested) {
        objects + nestedDeclarations.filterIsInstance<KoObject>()
    } else {
        objects
    }

    fun properties(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoProperty>()
    } else {
        properties
    }

    fun functions(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations.filterIsInstance<KoFunction>()
    } else {
        functions
    }

    fun declarations(includeNested: Boolean = false) = if (includeNested) {
        nestedDeclarations + properties + functions
    } else {
        declarations
    }
}
