package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.util.TypeResolver
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

internal class KoAnnotationDeclarationCore private constructor(
    override val ktAnnotationEntry: KtAnnotationEntry,
    // Declaration annotated with this annotation (e.g. used to check if the annotation is suppressed by the declaration)
    internal val annotatedDeclaration: KoBaseDeclaration,
) : KoAnnotationDeclaration,
    KoBaseProviderCore,
    KoArgumentProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoRepresentsTypeProviderCore,
    KoTextProviderCore,
    KoSourceDeclarationProviderCore {
    override val psiElement: PsiElement
        get() = ktAnnotationEntry

    override val ktElement: KtElement
        get() = ktAnnotationEntry

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() = KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) { ktAnnotationEntry.shortName.toString() }

    // Qualified name is used to resolve the nested and fully qualified annotations (e.g. `@Outer.Annotation`)
    override val stringUsedAsFullyQualifiedName: String
        get() = TypeUtil.getTypeName(ktAnnotationEntry.typeReference) ?: name

    /*
     * Nested types of the annotated declaration and its containing declarations are visible in the annotation, e.g.
     * `@Inner` used on a member of `Outer` refers to `Outer.Inner`. Nested types of a class, interface or object
     * are not visible in its own annotations, so resolution starts from its containing declaration.
     */
    override val sourceDeclarationResolutionScope: KoBaseDeclaration
        get() =
            when (annotatedDeclaration) {
                is KoClassDeclaration, is KoInterfaceDeclaration, is KoObjectDeclaration -> {
                    (annotatedDeclaration as? KoContainingDeclarationProvider)?.containingDeclaration ?: containingFile
                }

                else -> {
                    annotatedDeclaration
                }
            }

    @Volatile
    private var cachedArguments: Any? = KoPropertyCache.NOT_COMPUTED

    override val arguments: List<KoArgumentDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedArguments, { cachedArguments = it }) {
                ktAnnotationEntry
                    .children
                    .filterIsInstance<KtValueArgumentList>()
                    .firstOrNull()
                    ?.children
                    ?.filterIsInstance<KtValueArgument>()
                    ?.map { KoArgumentDeclarationCore.getInstance(it, this) }
                    .orEmpty()
            }

    @Volatile
    private var cachedSourceDeclaration: Any? = KoPropertyCache.NOT_COMPUTED

    override val sourceDeclaration: KoDeclarationCastProvider?
        get() =
            KoPropertyCache.getOrCompute(cachedSourceDeclaration, { cachedSourceDeclaration = it }) {
                // When sourceDeclaration is an import alias, its sourceDeclaration refers to the import.
                // The goal is to unwrap it and access the underlying base declaration directly.
                if (super.sourceDeclaration is KoImportAliasDeclaration) {
                    super.sourceDeclaration?.asImportAliasDeclaration()?.sourceDeclaration
                } else {
                    super.sourceDeclaration
                }
            }

    @Volatile
    private var cachedFullyQualifiedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val fullyQualifiedName: String?
        get() =
            KoPropertyCache.getOrCompute(cachedFullyQualifiedName, { cachedFullyQualifiedName = it }) {
                TypeResolver.resolveFullyQualifiedName(stringUsedAsFullyQualifiedName, containingFile, sourceDeclarationResolutionScope)
                    // Fallback: return the name if the type cannot be resolved
                    ?: stringUsedAsFullyQualifiedName
            }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoAnnotationDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktAnnotationEntry: KtAnnotationEntry,
            containingDeclaration: KoBaseDeclaration,
        ): KoAnnotationDeclaration =
            cache.getOrCreateInstance(ktAnnotationEntry, containingDeclaration) {
                KoAnnotationDeclarationCore(
                    ktAnnotationEntry,
                    containingDeclaration,
                )
            }
    }
}
