package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

/*
Instances are created atomically, so all threads (tests can run in parallel) get the same instance for the given key.
Declarations are compared by identity (e.g. when removing duplicated parents), so two instances of the same declaration
would break the comparison.
 */
internal class KoDeclarationCache<T : KoBaseProvider> {
    private val elements = ConcurrentHashMap<Pair<KtElement, KoBaseProvider?>, T>()

    fun getOrCreateInstance(
        ktElement: KtElement,
        containingDeclaration: KoBaseDeclaration,
        value: (KtElement) -> T,
    ): T {
        val cacheKey = ktElement to containingDeclaration

        return elements[cacheKey] ?: value(ktElement).let { elements.putIfAbsent(cacheKey, it) ?: it }
    }
}

internal object KoExternalDeclarationCache {
    private val elements = ConcurrentHashMap<String, KoExternalDeclaration>()

    fun getOrCreateInstance(
        key: String,
        ktElement: KtElement,
        value: (ktElement: KtElement) -> KoExternalDeclaration,
    ): KoExternalDeclaration = elements[key] ?: value(ktElement).let { elements.putIfAbsent(key, it) ?: it }
}
