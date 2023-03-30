package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation

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

    private val declarations by lazy {
        ktFile.declarations.mapNotNull {
            if (it is KtClass && !it.isInterface()) {
                KoClass(it)
            } else if (it is KtClass && it.isInterface()) {
                KoInterface(it)
            } else if (it is KtObjectDeclaration) {
                KoObject(it)
            } else if (it is KtProperty) {
                KoProperty(it)
            } else if (it is KtFunction) {
                KoFunction(it)
            } else {
                println("Warning unsupported kt declaration: ${ktFile.declarations.first().getTextWithLocation()}")
                null
            }
        }
    }

    fun classes(includeNested: Boolean = false): List<KoClass> = getDeclarations(includeNested)

    fun interfaces(includeNested: Boolean = false): List<KoInterface> = getDeclarations(includeNested)

    fun objects(includeNested: Boolean = false): List<KoObject> = getDeclarations(includeNested)

    fun properties(includeNested: Boolean = false): List<KoProperty> = getDeclarations(includeNested)

    fun functions(includeNested: Boolean = false): List<KoFunction> = getDeclarations(includeNested)

    fun declarations(includeNested: Boolean = false): List<KoDeclaration> = getDeclarations(includeNested)

    fun hasFunction(name: String, includeNested: Boolean = false) = functions(includeNested).any { it.name == name }

    fun hasProperty(name: String, includeNested: Boolean = false) = properties(includeNested).any { it.name == name }

    fun hasClass(name: String, includeNested: Boolean = false) = classes(includeNested).any { it.name == name }

    fun hasInterface(name: String, includeNested: Boolean = false) = interfaces(includeNested).any { it.name == name }

    fun hasObject(name: String, includeNested: Boolean = false) = objects(includeNested).any { it.name == name }

    fun hasImport(name: String) = imports.any { it.name == name }

    private inline fun <reified T : KoDeclaration>getDeclarations(includeNested: Boolean = false): List<T> {
        if (!includeNested) {
            return declarations.filterIsInstance<T>()
        }

        return declarations.flatMap {
            when (it) {
                is KoComplexDeclaration -> (listOf(it) + it.declarations(true)).filterIsInstance<T>()
                is T -> listOf(it)
                else -> listOf()
            }
        }
    }
}
