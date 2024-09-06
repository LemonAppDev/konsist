package com.lemonappdev.konsist.api

/**
 * Represents a KDoc tag.
 */
enum class KoKDocTag(
    val type: String,
    val isValued: Boolean = false,
) {
    /**
     * The `@param` tag.
     */
    PARAM("@param", true),

    /**
     * The `@return` tag.
     */
    RETURN("@return"),

    /**
     * The `@constructor` tag.
     */
    CONSTRUCTOR("@constructor"),

    /**
     * The `@receiver` tag.
     */
    RECEIVER("@receiver"),

    /**
     * The `@property` tag.
     */
    PROPERTY("@property", true),

    /**
     * The `@throws` tag.
     */
    THROWS("@throws", true),

    /**
     * The `@exception` tag.
     */
    EXCEPTION("@exception", true),

    /**
     * The `@sample` tag.
     */
    SAMPLE("@sample", true),

    /**
     * The `@see` tag.
     */
    SEE("@see", true),

    /**
     * The `@author` tag.
     */
    AUTHOR("@author"),

    /**
     * The `@since` tag.
     */
    SINCE("@since"),

    /**
     * The `@suppress` tag.
     */
    SUPPRESS("@suppress"),

    /**
     * The `@version` tag.
     */
    VERSION("@version"),

    /**
     * The `@propertySetter` tag.
     */
    PROPERTY_SETTER("@propertySetter"),

    /**
     * The `@propertyGetter` tag.
     */
    PROPERTY_GETTER("@propertyGetter"),
}
