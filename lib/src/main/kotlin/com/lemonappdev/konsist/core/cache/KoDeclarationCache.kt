package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import org.jetbrains.kotlin.com.intellij.openapi.util.Key
import org.jetbrains.kotlin.com.intellij.openapi.util.UserDataHolderEx
import org.jetbrains.kotlin.psi.KtElement
import java.lang.ref.WeakReference

/*
Declarations are stored in the user data of the PSI element, so the cache does not need a map entry (with a key object)
for every declaration, and declarations are released together with the PSI tree.

Instances are created atomically, so all threads (tests can run in parallel) get the same instance for the given key.
Declarations are compared by identity (e.g. when removing duplicated parents), so two instances of the same declaration
would break the comparison.
 */
internal class KoDeclarationCache<T : KoBaseProvider> {
    private val key = Key.create<Entry<T>>("KoDeclarationCache")

    fun getOrCreateInstance(
        ktElement: KtElement,
        containingDeclaration: KoBaseDeclaration,
        value: (KtElement) -> T,
    ): T =
        ktElement.getUserData(key)?.getInstance(containingDeclaration)
            ?: putIfAbsent(ktElement, containingDeclaration, value(ktElement))

    private tailrec fun putIfAbsent(
        ktElement: KtElement,
        containingDeclaration: KoBaseDeclaration,
        instance: T,
    ): T {
        val entry = ktElement.getUserData(key)

        entry?.getInstance(containingDeclaration)?.let { return it }

        val isStored = (ktElement as UserDataHolderEx).replace(key, entry, Entry(containingDeclaration, instance, entry))

        return if (isStored) instance else putIfAbsent(ktElement, containingDeclaration, instance)
    }

    /**
     * Declaration instances of the element for each containing declaration. Element usually has one containing
     * declaration (e.g. parameter defining property has a different one for parameter and property declaration).
     */
    private class Entry<T>(
        private val containingDeclaration: KoBaseProvider?,
        private val instance: T,
        private val next: Entry<T>?,
    ) {
        fun getInstance(containingDeclaration: KoBaseProvider?): T? =
            if (this.containingDeclaration == containingDeclaration) {
                instance
            } else {
                next?.getInstance(containingDeclaration)
            }
    }
}

/*
External declaration references the element where it was found for the first time, so it would keep the PSI tree of
that file in memory. Declarations are cached using weak references, so the same instance is returned as long as it is
used (e.g. by the parent declarations of other files).
 */
internal object KoExternalDeclarationCache {
    private val elements = KoReferenceCache<KoExternalDeclaration> { WeakReference(it) }

    fun getOrCreateInstance(
        key: String,
        value: () -> KoExternalDeclaration,
    ): KoExternalDeclaration = elements.getOrCreateInstance(key, value)
}
