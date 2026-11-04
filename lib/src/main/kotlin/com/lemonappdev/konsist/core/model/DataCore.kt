package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.provider.util.KoFileDeclarationProvider
import kotlinx.coroutines.runBlocking
import java.lang.ref.SoftReference

/**
 * Data of the whole project. Data is computed when needed and cached using soft references, so it can be released from
 * memory (together with parsed files) when the memory is low.
 */
internal object DataCore {
    val projectDeclarationIndex =
        ProjectDeclarationIndex(
            getFiles = {
                runBlocking { KoFileDeclarationProvider.getProjectKotlinFiles() }
                    .filterNot { KoFileDeclarationProvider.isBuildConfigFile(it.path) }
            },
            getPackageName = KoFileDeclarationProvider::getPackageName,
            getKoFile = { it.toKoFile() },
        )

    @Volatile
    private var childrenIndexReference: SoftReference<ChildrenIndex>? = null

    private val childrenIndex: ChildrenIndex
        get() =
            childrenIndexReference?.get() ?: synchronized(this) {
                childrenIndexReference?.get() ?: createChildrenIndex().also { childrenIndexReference = SoftReference(it) }
            }

    /**
     * Returns classes, interfaces and objects of the project that have the given declaration as a parent.
     */
    fun getChildren(
        declaration: KoBaseProvider,
        indirectChildren: Boolean,
    ): List<KoChildDeclaration> = childrenIndex.getChildren(declaration as? KoSourceDeclaration, indirectChildren)

    private fun createChildrenIndex(): ChildrenIndex {
        val scope = Konsist.scopeFromProject()
        return ChildrenIndex(scope.classes() + scope.interfaces() + scope.objects())
    }
}
