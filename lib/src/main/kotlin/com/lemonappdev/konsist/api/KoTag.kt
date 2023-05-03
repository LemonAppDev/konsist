package com.lemonappdev.konsist.api

enum class KoTag(val type: String, val isValued: Boolean = false) {
    PARAM("@param", true),
    RETURN("@return"),
    CONSTRUCTOR("@constructor"),
    RECEIVER("@receiver"),
    PROPERTY("@property", true),
    THROWS("@throws", true),
    EXCEPTION("@exception", true),
    SAMPLE("@sample", true),
    SEE("@see", true),
    AUTHOR("@author"),
    SINCE("@since"),
    SUPPRESS("@suppress"),
    VERSION("@version"),
    PROPERTY_SETTER("@propertySetter"),
    PROPERTY_GETTER("@propertyGetter"),
}
