# Configure Konsist

# Add Dependency To Test Source Set

`Konsist` can be added to the project using the `test` source set.  

![KonsistSourceSet.png](misc/TestSourceSet.png)

Add the following dependency to the `module\build.gradle.kts` file:

```kotlin
dependencies {
    testImplementation(KONSIST_DEPENDENCY)
}
```

# Create KonsistTest Source Set

For better test separation a new `konsistTest` source set can be defined using the 
[JVM Test Suite Plugin](https://docs.gradle.org/current/userguide/jvm_test_suite_plugin.html).

![KonsistSourceSet.png](misc/KonsistTestSourceSet.png)

Add the following `testing` block configuration to the `module\build.gradle.kts` file:

```kotlin
testing {
    suites {
        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                // KONSIST_DEPENDENCY
            }
        }
    }
}
```
