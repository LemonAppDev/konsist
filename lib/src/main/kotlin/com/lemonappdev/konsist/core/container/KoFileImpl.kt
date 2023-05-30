package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.LocationHelper
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import kotlin.reflect.KClass

internal class KoFileImpl(private val ktFile: KtFile) : KoFile {

    override val name by lazy { nameWithExtension.substringBeforeLast('.') }

    override val extension: String by lazy { nameWithExtension.substringAfterLast('.') }

    override val nameWithExtension: String by lazy {
        ktFile
            .name
            .split("/")
            .last()
    }

    override val path: String by lazy { ktFile.name }

    override val rootProjectPath by lazy {
        val rootPathProvider = PathProvider
            .getInstance()
            .rootProjectPath

        path.removePrefix(rootPathProvider)
    }

    override val moduleName: String by lazy {
        val projectName = PathProvider
            .getInstance()
            .rootProjectPath
            .substringAfterLast('/')

        val moduleName = rootProjectPath
            .substringBefore("/src/")
            .substringAfter("/")

        if (moduleName == projectName || moduleName == "") {
            "root"
        } else {
            moduleName
        }
    }

    override val sourceSetName: String by lazy {
        rootProjectPath
            .substringAfter("/src/")
            .substringBefore("/")
    }

    override val text by lazy {
        ktFile
            .getTextWithLocation()
            .substringBefore("' at (")
            .removePrefix("'")
            .removeSuffix("\n")
    }

    override val imports: List<KoImportDeclaration> by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImportDeclarationImpl.getInstance(it, null) }
    }

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        ktFile
            .annotationEntries
            .map { KoAnnotationDeclarationImpl.getInstance(it, null) }
    }

    override val packagee: KoPackageDeclaration? by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackageDeclarationImpl.getInstance(it, null) }
        }
    }

    override val typeAliases: List<KoTypeAliasDeclaration> by lazy {
        ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAliasDeclarationImpl.getInstance(it, null) }
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoNamedDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(ktFile, includeNested, includeLocal, null)

    override fun hasAnnotations(vararg names: String): Boolean = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all {
            annotations.any { annotation ->
                annotation.fullyQualifiedName.substringAfterLast(".") == it || annotation.fullyQualifiedName == it
            }
        }
    }

    override fun hasAnnotationsOf(vararg names: KClass<*>): Boolean = names.all {
        annotations.any { annotation -> annotation.fullyQualifiedName == it.qualifiedName }
    }

    override fun hasPackage(name: String): Boolean = packagee
        ?.qualifiedName
        ?.let { LocationHelper.resideInLocation(name, it) } ?: false

    override fun hasImports(vararg names: String): Boolean = when {
        names.isEmpty() -> imports.isNotEmpty()
        else -> names.all {
            imports.any { import -> LocationHelper.resideInLocation(it, import.name) }
        }
    }

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }

    override fun resideInPath(path: String) = LocationHelper.resideInLocation(path, this.path)

    override fun resideInRootProjectPath(path: String) = LocationHelper.resideInLocation(path, rootProjectPath)

    override fun resideInModule(module: String): Boolean = module == moduleName

    override fun resideInSourceSet(sourceSet: String): Boolean = sourceSet == sourceSetName

    override fun hasNameStartingWith(prefix: String) = name.startsWith(prefix)

    override fun hasNameEndingWith(suffix: String) = name.endsWith(suffix)

    override fun hasNameContaining(text: String) = name.contains(text)

    override fun hasNameMatching(regex: Regex) = name.matches(regex)

    override fun hasExtension(extension: String): Boolean = extension == this.extension

    override fun equals(other: Any?): Boolean = other is KoFile && path == other.path

    override fun hashCode(): Int = 31 * 7 + path.hashCode()
}
