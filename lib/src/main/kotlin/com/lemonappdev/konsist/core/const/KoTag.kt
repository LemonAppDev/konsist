package com.lemonappdev.konsist.core.const

enum class KoTag(val type: String) {
    PARAM("@param"),
    RETURN("@return"),
    CONSTRUCTOR("@constructor"),
    RECEIVER("@receiver"),
    PROPERTY("@property"),
    THROWS("@throws"),
    EXCEPTION("@exception"),
    SAMPLE("@sample"),
    SEE("@see"),
    AUTHOR("@author"),
    SINCE("@since"),
    SUPPRESS("@suppress"),
    VERSION("@version"),
    PROPERTYSETTER("@propertySetter"),
    PROPERTYGETTER("@propertyGetter");
}
