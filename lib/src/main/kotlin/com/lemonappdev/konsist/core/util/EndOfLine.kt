package com.lemonappdev.konsist.core.util

internal enum class EndOfLine(val value: String) {
    /**
     * Unix-style end-of-line marker (LF)
     */
    UNIX("\n"),

    /**
     * Windows-style end-of-line marker (CRLF)
     */
    WINDOWS("\r\n"),
}
