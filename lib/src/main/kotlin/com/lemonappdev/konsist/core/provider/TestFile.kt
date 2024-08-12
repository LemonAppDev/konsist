package nice

annotation class Frank

@Frank
interface ContractA {
    fun method(arg: Foo): Foo

    data class Foo(val bar: String)
}

@Frank
interface ContractB {
    fun method(arg: Foo): Foo

    data class Foo(val nice: String)
}

@Frank
interface ContractC {
    fun method(arg: Foo):  Foo

    data class Foo(val other: String)
}

// Todo: remove file



interface FirstInterface {
    class NestedClass(val arg: SampleNestedObjectWithTheSameName?)
    object SampleNestedObjectWithTheSameName
}

interface SecondInterface {
    class NestedClass(val arg: SampleNestedObjectWithTheSameName?)
    object SampleNestedObjectWithTheSameName
}
