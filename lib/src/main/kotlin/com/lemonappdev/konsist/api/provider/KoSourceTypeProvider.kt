package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its source type.
 */
interface KoSourceTypeProvider : KoBaseProvider {
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
