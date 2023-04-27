
fun <T : KoBaseDeclaration> Sequence<T>.withParentDeclaration(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasParentDeclaration()
        else -> names.any { name -> it.hasParentDeclaration(name) }
    }
}

fun <T : KoBaseDeclaration> Sequence<T>.withoutParentDeclaration(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasParentDeclaration()
        else -> names.none { name -> it.hasParentDeclaration(name) }
    }
}
