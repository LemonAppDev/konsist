package com.lemonappdev.konsist.api

enum class KoTag(val type: String, val isValued: Boolean = false) {
    /**
     * The '@param' tag.
     */
    PARAM("@param", true),

    /**
     * The '@return' tag.
     */
    RETURN("@return"),

    /**
     * The '@constructor' tag.
     */
    CONSTRUCTOR("@constructor"),

    /**
     * The '@receiver' tag.
     */
    RECEIVER("@receiver"),

    /**
     * The ', tru' tag.
     */
    PROPERTY("@property", true),

    /**
     * The ', tru' tag.
     */
    THROWS("@throws", true),

    /**
     * The ', tru' tag.
     */
    EXCEPTION("@exception", true),

    /**
     * The ', tru' tag.
     */
    SAMPLE("@sample", true),

    /**
     * The ', tru' tag.
     */
    SEE("@see", true),

    /**
     * The '@author' tag.
     */
    AUTHOR("@author"),

    /**
     * The '@since' tag.
     */
    SINCE("@since"),

    /**
     * The '@suppress' tag.
     */
    SUPPRESS("@suppress"),

    /**
     * The '@version' tag.
     */
    VERSION("@version"),

    /**
     * The '@propertySetter' tag.
     */
    PROPERTY_SETTER("@propertySetter"),

    /**
     * The '@propertyGetter' tag.
     */
    PROPERTY_GETTER("@propertyGetter"),
}
