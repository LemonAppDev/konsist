package com.lemonappdev.sample

class LibTestClass1 {
    val cut = LibClass("")
}

class LibTestClass2 {
    val cut: LibClass = getInstance()
}

class LibTestClass3 {
    val cut: LibClass
        get() = LibClass("")
}

class LibTestClass4 {
    val cut
        get() = LibClass("")
}

class LibTestClass5 {
    fun sampleTest() {
        val cut = LibClass("")
    }
}

class LibTestClass6 {
    fun sampleTest() {
        val cut: LibClass = getInstance()
    }
}

class LibTestClass7 {
    fun sampleTest() {
        val sut = getInstance()
    }
}

private fun getInstance() = LibClass("")
