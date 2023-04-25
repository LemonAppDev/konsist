/**
 * This is a sample class that demonstrates the usage of KDoc tags.
 *
 * @param T The type parameter for this class.
 * @property name The name property of the class.
 * @constructor Creates a new instance of the [SampleClass].
 * @throws IllegalArgumentException If the [name] parameter is empty.
 * @see AnotherClass
 * @since 1.0.0
 * @version 1.2.3
 * @sample SampleClass.sampleMethod
 * @suppress UnusedPrivateMember
 */
class SampleClass<T>(private val name: String) {

    /**
     * This is a sample method that demonstrates the usage of KDoc tags.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @return The result of the computation.
     * @throws IllegalStateException If the computation fails.
     * @sample SampleClass.sampleMethod
     * @see AnotherClass
     * @since 1.0.0
     * @version 1.2.3
     * @suppress UnusedPrivateMember
     */
    fun sampleMethod(arg1: Int, arg2: String): T {
        // Implementation
    }

    /**
     * This is a sample property that demonstrates the usage of KDoc tags.
     *
     * @propertySetter Sets the value of the [name] property.
     * @propertyGetter Retrieves the value of the [name] property.
     * @since 1.0.0
     * @version 1.2.3
     * @suppress UnusedPrivateMember
     */
    var sampleProperty: T
        get() {
            // Getter implementation
        }
        set(value) {
            // Setter implementation
        }

    /**
     * This is a sample enum class that demonstrates the usage of KDoc tags.
     *
     * @since 1.0.0
     * @version 1.2.3
     * @suppress UnusedPrivateMember
     */
    enum class SampleEnum {
        VALUE1, VALUE2
    }
}

/**
 * This is a sample top-level function that demonstrates the usage of KDoc tags.
 *
 * @param arg1 The first argument.
 * @param arg2 The second argument.
 * @return The result of the computation.
 * @throws IllegalStateException If the computation fails.
 * @since 1.0.0
 * @version 1.2.3
 * @suppress UnusedPrivateMember
 */
fun sampleFunction(arg1: Int, arg2: String): String {
    // Implementation
}
