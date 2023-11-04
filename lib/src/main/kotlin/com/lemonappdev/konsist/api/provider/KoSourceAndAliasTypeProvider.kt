package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its source type and import alias name.
 */
interface KoSourceAndAliasTypeProvider : KoBaseProvider {
    /**
     * The import alias name.
     *
     * For `import com.app.MyClass as MyAlias` it will be "MyAlias".
     */
    val aliasType: String?

    /**
     * Returns `true` if this type is defined by the import alias.
     *
     * For the type import `import com.app.MyClass as MyAlias` the `isAlias` will be `true`.
     * For the type import `import com.app.MyClass` the `isAlias` will be `false`.
     */
    val isAlias: Boolean

    /**
     * The source type.
     * For `val car:MyClass` it will be "MyClass".
     * For `val car:MyClass<String>` it will be "MyClass<String>".
     *
     *  @see bareSourceType
     */
    val sourceType: String

    /**
     * The source type without generic type arguments and nullability ("?").
     *
     * For `val car:MyClass` bareSourceType will be "MyClass".
     * For `val car:MyClass?` bareSourceType will be "MyClass".
     * For `val car:MyClass<String>` bareSourceType will be "MyClass"
     * For `val car:MyClass<String?>?` bareSourceType will be "MyClass"
     *
     * @see sourceType
     */
    val bareSourceType: String
}
