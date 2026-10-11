package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider

internal object MutableTypeUtil {
    private const val MUTABLE_TYPE_PREFIX = "Mutable"

    /*
     * Checks if the source declaration is a mutable type.
     * Type aliases and import aliases are resolved to the declaration they point to, so their own names are ignored.
     */
    internal fun isMutableType(sourceDeclaration: KoDeclarationCastProvider?): Boolean =
        when (val declaration = sourceDeclaration.resolveImportAlias()) {
            is KoTypeAliasDeclaration -> declaration.type.isMutableType
            is KoKotlinTypeDeclaration -> isKnownMutableTypeName(declaration.name)
            is KoExternalDeclaration -> isKnownMutableTypeName(declaration.name)
            is KoClassDeclaration -> declaration.name.startsWith(MUTABLE_TYPE_PREFIX)
            is KoInterfaceDeclaration -> declaration.name.startsWith(MUTABLE_TYPE_PREFIX)
            is KoObjectDeclaration -> declaration.name.startsWith(MUTABLE_TYPE_PREFIX)
            else -> false
        }

    /*
     * Checks if any type argument (at any nesting level) is a mutable type.
     * For a type alias, type arguments of the aliased type are checked as well.
     */
    internal fun hasMutableTypeArgument(
        sourceDeclaration: KoDeclarationCastProvider?,
        typeArguments: List<KoTypeArgumentDeclaration>?,
    ): Boolean =
        (sourceDeclaration.resolveImportAlias() as? KoTypeAliasDeclaration)?.type?.hasMutableTypeArgument == true ||
            typeArguments.orEmpty().any {
                isMutableType(it.sourceDeclaration) || hasMutableTypeArgument(it.sourceDeclaration, it.typeArguments)
            }

    private fun KoDeclarationCastProvider?.resolveImportAlias(): KoDeclarationCastProvider? =
        if (this is KoImportAliasDeclaration) sourceDeclaration else this

    private fun isKnownMutableTypeName(name: String): Boolean {
        val bareName = TypeUtil.getBareType(name)

        return bareName.startsWith(MUTABLE_TYPE_PREFIX) || bareName in knownMutableTypeNames
    }

    // Mutable Kotlin and Java types whose names do not start with "Mutable"
    private val knownMutableTypeNames: Set<String> =
        setOf(
            // Kotlin collections
            "AbstractMutableCollection",
            "AbstractMutableList",
            "AbstractMutableMap",
            "AbstractMutableSet",
            "ArrayDeque",
            "ArrayList",
            "HashMap",
            "HashSet",
            "LinkedHashMap",
            "LinkedHashSet",
            // Kotlin arrays
            "Array",
            "BooleanArray",
            "ByteArray",
            "CharArray",
            "DoubleArray",
            "FloatArray",
            "IntArray",
            "LongArray",
            "ShortArray",
            "UByteArray",
            "UIntArray",
            "ULongArray",
            "UShortArray",
            // Java collections
            "ArrayBlockingQueue",
            "ConcurrentHashMap",
            "ConcurrentLinkedDeque",
            "ConcurrentLinkedQueue",
            "ConcurrentSkipListMap",
            "ConcurrentSkipListSet",
            "CopyOnWriteArrayList",
            "CopyOnWriteArraySet",
            "EnumMap",
            "Hashtable",
            "IdentityHashMap",
            "LinkedBlockingDeque",
            "LinkedBlockingQueue",
            "LinkedList",
            "PriorityBlockingQueue",
            "PriorityQueue",
            "Stack",
            "TreeMap",
            "TreeSet",
            "Vector",
            "WeakHashMap",
            // String builders
            "StringBuffer",
            "StringBuilder",
            // Atomics
            "AtomicArray",
            "AtomicBoolean",
            "AtomicInt",
            "AtomicIntArray",
            "AtomicInteger",
            "AtomicIntegerArray",
            "AtomicLong",
            "AtomicLongArray",
            "AtomicReference",
            "AtomicReferenceArray",
        )
}
