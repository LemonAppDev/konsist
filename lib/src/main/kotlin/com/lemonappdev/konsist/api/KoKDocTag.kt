package com.lemonappdev.konsist.api

/**
 * Represents a KDoc tag.
 */
enum class KoKDocTag(
    val type: String,
    val isValued: Boolean = false,
) {
    /**
     * The `@author` tag.
     */
    AUTHOR("@author"),

    /**
     * The `@constructor` tag.
     */
    CONSTRUCTOR("@constructor"),

    /**
     * The `@exception` tag.
     */
    EXCEPTION("@exception", true),

    /**
     * The `@param` tag.
     */
    PARAM("@param", true),

    /**
     * The `@property` tag.
     */
    PROPERTY("@property", true),

    /**
     * The `@propertyGetter` tag.
     */
    PROPERTY_GETTER("@propertyGetter"),

    /**
     * The `@propertySetter` tag.
     */
    PROPERTY_SETTER("@propertySetter"),

    /**
     * The `@receiver` tag.
     */
    RECEIVER("@receiver"),

    /**
     * The `@return` tag.
     */
    RETURN("@return"),

    /**
     * The `@sample` tag.
     */
    SAMPLE("@sample", true),

    /**
     * The `@see` tag.
     */
    SEE("@see", true),

    /**
     * The `@since` tag.
     */
    SINCE("@since"),

    /**
     * The `@suppress` tag.
     */
    SUPPRESS("@suppress"),

    /**
     * The `@throws` tag.
     */
    THROWS("@throws", true),

    /**
     * The `@version` tag.
     */
    VERSION("@version"),
}
