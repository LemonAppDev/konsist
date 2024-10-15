package com.lemonappdev.konsist.api

/**
 * Represents a Kotlin modifier.
 */
enum class KoModifier(
    val type: String,
) {
    /**
     * The `abstract` modifier.
     */
    ABSTRACT("abstract"),

    /**
     * The `actual` modifier.
     */
    ACTUAL("actual"),

    /**
     * The `annotation` modifier.
     */
    ANNOTATION("annotation"),

    /**
     * The `companion` modifier.
     */
    COMPANION("companion"),

    /**
     * The `const` modifier.
     */
    CONST("const"),

    /**
     * The `contract` modifier.
     */
    CONTRACT("contract"),

    /**
     * The `crossinline` modifier.
     */
    CROSSINLINE("crossinline"),

    /**
     * The `data` modifier.
     */
    DATA("data"),

    /**
     * The `num` modifier.
     */
    ENUM("enum"),

    /**
     * The `expect` modifier.
     */
    EXPECT("expect"),

    /**
     * The `external` modifier.
     */
    EXTERNAL("external"),

    /**
     * The `final` modifier.
     */
    FINAL("final"),

    /**
     * The `fun` modifier.
     */
    FUN("fun"),

    /**
     * The `in` modifier.
     */
    IN("in"),

    /**
     * The `infix` modifier.
     */
    INFIX("infix"),

    /**
     * The `inline` modifier.
     */
    INLINE("inline"),

    /**
     * The `inner` modifier.
     */
    INNER("inner"),

    /**
     * The `internal` modifier.
     */
    INTERNAL("internal"),

    /**
     * The `lateinit` modifier.
     */
    LATEINIT("lateinit"),

    /**
     * The `noinline` modifier.
     */
    NOINLINE("noinline"),

    /**
     * The `open` modifier.
     */
    OPEN("open"),

    /**
     * The `operator` modifier.
     */
    OPERATOR("operator"),

    /**
     * The `out` modifier.
     */
    OUT("out"),

    /**
     * The `override` modifier.
     */
    OVERRIDE("override"),

    /**
     * The `private` modifier.
     */
    PRIVATE("private"),

    /**
     * The `protected` modifier.
     */
    PROTECTED("protected"),

    /**
     * The `public` modifier.
     */
    PUBLIC("public"),

    /**
     * The `reified` modifier.
     */
    REIFIED("reified"),

    /**
     * The `sealed` modifier.
     */
    SEALED("sealed"),

    /**
     * The `suspend` modifier.
     */
    SUSPEND("suspend"),

    /**
     * The `tailrec` modifier.
     */
    TAILREC("tailrec"),

    /**
     * The `value` modifier.
     */
    VALUE("value"),

    /**
     * The `vararg` modifier.
     */
    VARARG("vararg"),
}
