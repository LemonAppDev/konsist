package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its source type and import alias name.
 */
interface KoSourceAndAliasTypeProvider : KoBaseProvider {
    /**
     * Returns `true` if this type is defined by the import alias.
     *
     * For the type import `import com.app.MyClass as MyAlias` the `isAlias` will be `true`.
     * For the type import `import com.app.MyClass` the `isAlias` will be `false`.
     */
    @Deprecated("Will be removed in version 0.18.0", ReplaceWith("isImportAlias"))
    val isAlias: Boolean

    /**
     * Returns `true` if this type is defined by the import alias.
     *
     * For the type import `import com.app.MyClass as MyAlias` the `isImportAlias` will be `true`.
     * For the type import `import com.app.MyClass` the `isImportAlias` will be `false`.
     */
    val isImportAlias: Boolean

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
     * For `val car:MyClass` value will be "MyClass".
     * For `val car:MyClass?` value will be "MyClass".
     * For `val car:MyClass<String>` value will be "MyClass"
     * For `val car:MyClass<String?>?` value will be "MyClass"
     * For `val car:com.app.MyClass` value will be "MyClass"
     *
     * @see sourceType
     */
    val bareSourceType: String
}
