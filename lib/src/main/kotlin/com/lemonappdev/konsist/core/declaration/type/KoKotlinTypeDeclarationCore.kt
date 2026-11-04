package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.cache.KoReferenceCache
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceTypeProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import java.lang.ref.WeakReference

internal class KoKotlinTypeDeclarationCore private constructor(
    override val ktElement: KtElement,
    // Set for the type read from the classpath (e.g. indirect parent of the library class), which is not written in the code
    private val classpathFullyQualifiedName: String? = null,
) : KoKotlinTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoSourceTypeProviderCore {
    override val psiElement: PsiElement
        get() = ktElement

    @Volatile
    private var cachedPackagee: Any? = KoPropertyCache.NOT_COMPUTED

    override val packagee: KoPackageDeclaration?
        get() =
            KoPropertyCache.getOrCompute(cachedPackagee, { cachedPackagee = it }) {
                KoPackageDeclarationCore(
                    fullyQualifiedName,
                    ktElement,
                )
            }

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() =
            KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) {
                classpathFullyQualifiedName?.substringAfterLast(".") ?: TypeUtil.getBareType(ktElement.text)
            }

    override val text: String
        get() = if (classpathFullyQualifiedName != null) name else ktElement.text

    @Volatile
    private var cachedFullyQualifiedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val fullyQualifiedName: String
        get() =
            KoPropertyCache.getOrCompute(cachedFullyQualifiedName, { cachedFullyQualifiedName = it }) {
                classpathFullyQualifiedName ?: TypeUtil
                    .kotlinTypes
                    .firstOrNull { it.endsWith(".$stringUsedAsFullyQualifiedName") }
                    ?: throw IllegalArgumentException("Kotlin type has incorrect fullyQualified name")
            }

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoKotlinTypeDeclaration> = KoDeclarationCache()

        // Like external declarations, types read from the classpath are cached by the fully qualified name
        private val classpathCache: KoReferenceCache<KoKotlinTypeDeclaration> = KoReferenceCache { WeakReference(it) }

        internal fun getInstance(
            ktElement: KtElement,
            containingDeclaration: KoBaseDeclaration,
        ): KoKotlinTypeDeclaration =
            cache.getOrCreateInstance(ktElement, containingDeclaration) {
                KoKotlinTypeDeclarationCore(ktElement)
            }

        /*
         * Returns the type read from the classpath. [ktElement] is the element where the type was found for the first time
         * (e.g. the parent of the class through which the indirect parent was found).
         */
        internal fun getInstance(
            fullyQualifiedName: String,
            ktElement: KtElement,
        ): KoKotlinTypeDeclaration =
            classpathCache.getOrCreateInstance(fullyQualifiedName) {
                KoKotlinTypeDeclarationCore(ktElement, fullyQualifiedName)
            }
    }
}
