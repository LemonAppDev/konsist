package com.lemonappdev.konsist.api

/**
 * Represents a Kotlin modifier.
 */
enum class KoModifier(
    val type: String,
) {
    /**
     * The `data` modifier.
     */
    DATA("data"),

    /**
     * The `value` modifier.
     */
    VALUE("value"),

    /**
     * The `inline` modifier.
     */
    INLINE("inline"),

    /**
     * The `noinline` modifier.
     */
    NOINLINE("noinline"),

    /**
     * The `tailrec` modifier.
     */
    TAILREC("tailrec"),

    /**
     * The `external` modifier.
     */
    EXTERNAL("external"),

    /**
     * The `annotation` modifier.
     */
    ANNOTATION("annotation"),

    /**
     * The `crossinline` modifier.
     */
    CROSSINLINE("crossinline"),

    /**
     * The `operator` modifier.
     */
    OPERATOR("operator"),

    /**
     * The `infix` modifier.
     */
    INFIX("infix"),

    /**
     * The `abstract` modifier.
     */
    ABSTRACT("abstract"),

    /**
     * The `num` modifier.
     */
    ENUM("enum"),

    /**
     * The `contract` modifier.
     */
    CONTRACT("contract"),

    /**
     * The `open` modifier.
     */
    OPEN("open"),

    /**
     * The `inner` modifier.
     */
    INNER("inner"),

    /**
     * The `override` modifier.
     */
    OVERRIDE("override"),

    /**
     * The `private` modifier.
     */
    PRIVATE("private"),

    /**
     * The `public` modifier.
     */
    PUBLIC("public"),

    /**
     * The `internal` modifier.
     */
    INTERNAL("internal"),

    /**
     * The `protected` modifier.
     */
    PROTECTED("protected"),

    /**
     * The `out` modifier.
     */
    OUT("out"),

    /**
     * The `vararg` modifier.
     */
    VARARG("vararg"),

    /**
     * The `reified` modifier.
     */
    REIFIED("reified"),

    /**
     * The `companion` modifier.
     */
    COMPANION("companion"),

    /**
     * The `sealed` modifier.
     */
    SEALED("sealed"),

    /**
     * The `final` modifier.
     */
    FINAL("final"),

    /**
     * The `lateinit` modifier.
     */
    LATEINIT("lateinit"),

    /**
     * The `const` modifier.
     */
    CONST("const"),

    /**
     * The `suspend` modifier.
     */
    SUSPEND("suspend"),

    /**
     * The `expect` modifier.
     */
    EXPECT("expect"),

    /**
     * The `actual` modifier.
     */
    ACTUAL("actual"),

    /**
     * The `fun` modifier.
     */
    FUN("fun"),
}
