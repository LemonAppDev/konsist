plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(plugin(libs.plugins.kotlin.jvm))
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.springframework.boot))
    implementation(plugin(libs.plugins.spring.dependencyManagement))
    implementation(plugin(libs.plugins.kotlin.plugin.spring))
    implementation(plugin(libs.plugins.kotlin.plugin.jpa))
    implementation(plugin(libs.plugins.testLogger))
    implementation(plugin(libs.plugins.detekt))
}

fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
