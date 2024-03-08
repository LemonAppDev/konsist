package config

enum class ReleaseTarget(val value: String) {
    LOCAL("local"),
    SNAPSHOT("snapshot"),
    RELEASE("release"),
}
