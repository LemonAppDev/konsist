rootProject.name = "mango"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlinVersion", "1.8.10")

            plugin("springframework-boot", "org.springframework.boot").version("3.0.2")
            plugin("spring-dependencyManagement", "io.spring.dependency-management").version("1.1.0")
            plugin("kotlin-plugin-spring", "org.jetbrains.kotlin.plugin.spring").versionRef("kotlinVersion")
            plugin("kotlin-plugin-jpa", "org.jetbrains.kotlin.plugin.jpa").versionRef("kotlinVersion")
            plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlinVersion")
            plugin("spotless", "com.diffplug.spotless").version("6.15.0")
            plugin("testLogger", "com.adarshr.test-logger").version("3.2.0")
            plugin("detekt", "io.gitlab.arturbosch.detekt").version("1.22.0")

            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").withoutVersion()
            library("kotlin-stdlib-jdk8", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").withoutVersion()
            library("spring-boot-devtools", "org.springframework.boot", "spring-boot-devtools").withoutVersion()
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
            library("jacksonKotlin", "com.fasterxml.jackson.module", "jackson-module-kotlin").withoutVersion()
            library("jacksonJsr310", "com.fasterxml.jackson.datatype", "jackson-datatype-jsr310").withoutVersion()
            library("archunit", "com.tngtech.archunit", "archunit-junit5").version("1.0.1")
            library("h2", "com.h2database", "h2").version("2.1.214")

            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            library("spring-boot-starter-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").withoutVersion()
            library("junitJupiterEngine", "org.junit.jupiter", "junit-jupiter-engine").version("5.9.2")
            library("kluent", "org.amshove.kluent", "kluent").version("1.72")
            library("mockk", "io.mockk", "mockk").version("1.13.4")
        }
    }
}
