import com.lemonappdev.konsist.core.declaration.KoBaseDeclarationImpl

fun <T : KoBaseDeclarationImpl> Sequence<T>.withParentDeclaration(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentDeclaration()
        else -> names.any { name -> it.hasParentDeclaration(name) }
    }
}

fun <T : KoBaseDeclarationImpl> Sequence<T>.withoutParentDeclaration(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentDeclaration()
        else -> names.none { name -> it.hasParentDeclaration(name) }
    }
}
