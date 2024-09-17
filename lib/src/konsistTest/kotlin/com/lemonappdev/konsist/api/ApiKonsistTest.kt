package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParameter
import com.lemonappdev.konsist.api.ext.list.withProperty
import com.lemonappdev.konsist.api.ext.list.withoutAnnotationOf
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.ext.list.withoutNameMatching
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import java.util.Locale

class ApiKonsistTest {
    private val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")

    @Test
    fun `every api declaration has explicit return type`() {
        apiPackageScope
            .functions()
            .assertTrue { it.hasReturnType() }
    }

    @Test
    fun `every api function has valid KDoc`() {
        apiPackageScope
            .functions()
            .assertTrue { it.hasValidKDocParamTags() && it.hasValidKDocReturnTag() }
    }

    @Test
    fun `every api declaration has valid KDoc`() {
        apiPackageScope
            .classesAndInterfacesAndObjects()
            .assertTrue { it.hasKDoc }
    }

    @Test
    fun `includeNested parameter is always before includeLocal parameter`() {
        apiPackageScope
            .functions()
            .assertTrue {
                val includeNestedParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeNested" }
                val includeLocalParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeLocal" }

                includeNestedParameter <= includeLocalParameter || (includeNestedParameter != -1 && includeLocalParameter == -1)
            }
    }

    @Test
    fun `every api declaration implements KoBaseDeclaration and KoBaseProvider`() {
        apiPackageScope
            .interfaces()
            .withNameEndingWith("Declaration")
            .withoutName("KoBaseDeclaration")
            .assertTrue { it.hasParentsWithAllNames("KoBaseDeclaration", "KoBaseProvider", indirectParents = true) }
    }

    @Test
    fun `every api provider implements KoBaseProvider`() {
        apiPackageScope
            .interfaces()
            .withNameEndingWith("Provider")
            .withoutName("KoBaseProvider")
            .assertTrue { it.hasParentWithName("KoBaseProvider", indirectParents = true) }
    }

    @Test
    fun `none method has name containing 'Some'`() {
        apiPackageScope
            .functions()
            .assertFalse { it.hasNameContaining("Some") }
    }

    @Test
    fun `every provider has correct hasX methods`() {
        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.provider..", sourceSetName = "main")
            .interfaces()
            .withoutNameMatching(Regex("\\bKoKDoc[A-Za-z]+TagProvider\\b")) // exclude providers like KoKDocXTagProvider
            .withProperty { property ->
                !property.hasAnnotationOf<Deprecated>() &&
                    property.hasType { type ->
                        type.hasNameStartingWith("List<Ko")
                    }
            }.assertTrue {
                it.hasCorrectMethods(false)
            }
    }

    @Test
    fun `every method with vararg parameter calls the method with the same name and list parameter`() {
        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.ext..", sourceSetName = "main")
            .functions()
            .withoutAnnotationOf(Deprecated::class)
            .withParameter { it.hasVarArgModifier }
            .assertTrue { it.hasExpressionBody && it.hasTextContaining("${it.name}(listOf(") }
    }

    @Test
    fun `every provider containing property with list of declarations type has correct extensions`() {
        val providers =
            Konsist
                .scopeFromPackage("com.lemonappdev.konsist.api.provider..", sourceSetName = "main")
                .interfaces()
                .withoutNameMatching(Regex("\\bKoKDoc[A-Za-z]+TagProvider\\b")) // exclude providers like KoKDocXTagProvider
                .withProperty { property ->
                    !property.hasAnnotationOf<Deprecated>() &&
                        property.hasType { type ->
                            type.hasNameStartingWith("List<Ko")
                        }
                }.map { it.name }

        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.ext.list..", sourceSetName = "main")
            .files
            .filter {
                providers.any { providerName -> it.hasNameContaining(providerName) }
            }.assertTrue { it.hasCorrectMethods(true) }
    }

    private val declarations =
        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.declaration..", sourceSetName = "main")
            .interfaces()

    private fun <T> T.hasCorrectMethods(
        isExtension: Boolean,
    ): Boolean
            where T : KoPropertyProvider,
                  T : KoFunctionProvider {
        val property =
            properties()
                .firstOrNull { property ->
                    property.hasType { type ->
                        type.hasNameStartingWith("List<Ko")
                    }
                }

        val declarationName =
            property
                ?.type
                ?.name
                ?.removePrefix("List<")
                ?.removeSuffix(">")

        // We distinguish singularName and pluralName due to different variations of words,
        // e.g. import -> imports (suffix - "s") , but class -> classes (suffix - "es")
        val singularName =
            declarationName
                ?.removePrefix("Ko")
                ?.removeSuffix("Declaration")
                ?: ""

        val pluralName =
            property
                ?.name
                ?.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() }

        val hasKoNameProvider =
            declarations
                .firstOrNull { declaration -> declaration.name == declarationName }
                ?.hasParentInterfaceWithName("KoNameProvider", indirectParents = true) ?: false

        return if (isExtension) {
            pluralName?.let { checkForExtensions(declarationName, singularName, it, hasKoNameProvider) } ?: true
        } else {
            pluralName?.let { checkForProviders(declarationName, singularName, it, hasKoNameProvider) } ?: true
        }
    }

    @Suppress("detekt.CyclomaticComplexMethod")
    private fun KoFunctionProvider.checkForExtensions(
        declarationName: String?,
        singularName: String,
        pluralName: String,
        hasKoNameProvider: Boolean,
    ): Boolean =
        if (declarationName == "KoModifier") {
            checkForExceptions(singularName, pluralName, true)
        } else if (declarationName == "KoKDocTagDeclaration") {
            checkForExceptions("Tag", "Tags", true)
        } else if (declarationName == "KoFunctionDeclaration" || declarationName == "KoClassDeclaration") {
            checkForFunctionWithName(singularName, pluralName, true) ||
                checkForFunctionWithName("Local$singularName", pluralName, true)
        } else if (declarationName == "KoInterfaceDeclaration") {
            checkForFunctionWithName("Parent$singularName", pluralName, true)
        } else if (declarationName == "KoBaseDeclaration") {
            checkForFunctionWithoutName(singularName, pluralName, true) ||
                checkForFunctionWithoutName("LocalDeclaration", pluralName, true)
        } else if (hasKoNameProvider) {
            checkForFunctionWithName(singularName, pluralName, true)
        } else {
            checkForFunctionWithoutName(singularName, pluralName, true)
        }

    private fun KoFunctionProvider.checkForProviders(
        declarationName: String?,
        singularName: String,
        pluralName: String,
        hasKoNameProvider: Boolean,
    ): Boolean =
        if (declarationName == "KoModifier") {
            checkForExceptions(singularName, pluralName, false)
        } else if (declarationName == "KoKDocTagDeclaration") {
            checkForExceptions("Tag", "Tags", false)
        } else if (declarationName == "KoFunctionDeclaration" || declarationName == "KoClassDeclaration") {
            checkForFunctionWithName("Local$singularName", pluralName, false)
        } else if (declarationName == "KoInterfaceDeclaration") {
            checkForFunctionWithName("Parent$singularName", pluralName, false)
        } else if (declarationName == "KoBaseDeclaration") {
            checkForFunctionWithoutName("Declaration", pluralName, false) ||
                checkForFunctionWithoutName("LocalDeclaration", pluralName, false)
        } else if (hasKoNameProvider) {
            checkForFunctionWithName(singularName, pluralName, false)
        } else {
            checkForFunctionWithoutName(singularName, pluralName, false)
        }

    private fun KoFunctionProvider.hasExceptionFunctions(
        singularName: String,
        pluralName: String,
        prefix: String,
    ): Boolean =
        hasFunction { function -> function.name == "${prefix}$pluralName" && !function.hasParameters() } &&
            hasFunction { function ->
                function.name == "${prefix}$singularName" &&
                    function.hasParametersWithAllNames(
                        singularName.lowercase(),
                        pluralName.lowercase(),
                    )
            } &&
            hasFunction { function ->
                function.name == "${prefix}All$pluralName" &&
                    function.hasParametersWithAllNames(
                        singularName.lowercase(),
                        pluralName.lowercase(),
                    )
            }

    private fun KoFunctionProvider.hasBasicFunctions(
        singularName: String,
        pluralName: String,
        prefix: String,
    ): Boolean =
        hasFunction { function -> function.name == "${prefix}$pluralName" && !function.hasParameters() } &&
            hasFunction { function ->
                function.name == "${prefix}$singularName" &&
                    function.hasParameterWithName(
                        "predicate",
                    )
            } &&
            hasFunction { function ->
                function.name == "${prefix}All$pluralName" &&
                    function.hasParameterWithName(
                        "predicate",
                    )
            }

    private fun KoFunctionProvider.hasNamedFunctions(
        singularName: String,
        pluralName: String,
    ): Boolean =
        hasFunction { function ->
            function.name == "has${singularName}WithName" && function.hasParametersWithAllNames("name", "names")
        } &&
            hasFunction { function ->
                function.name == "has${pluralName}WithAllNames" &&
                    function.hasParametersWithAllNames(
                        "name",
                        "names",
                    )
            }

    private fun KoFunctionProvider.hasNamedFunctionsForExt(
        singularName: String,
        pluralName: String,
        prefix: String,
    ): Boolean =
        hasFunction { function ->
            function.name == "$prefix${singularName}Named" && function.hasParametersWithAllNames("name", "names")
        } &&
            hasFunction { function ->
                function.name == "${prefix}All${pluralName}Named" &&
                    function.hasParametersWithAllNames(
                        "name",
                        "names",
                    )
            }

    private fun KoFunctionProvider.checkForExceptions(
        singularName: String,
        pluralName: String,
        isExtension: Boolean,
    ): Boolean =
        if (isExtension) {
            hasExceptionFunctions(singularName, pluralName, "with") &&
                hasExceptionFunctions(singularName, pluralName, "without")
        } else {
            hasExceptionFunctions(singularName, pluralName, "has")
        }

    private fun KoFunctionProvider.checkForFunctionWithName(
        singularName: String,
        pluralName: String,
        isExtension: Boolean,
    ): Boolean =
        if (isExtension) {
            hasBasicFunctions(singularName, pluralName, "with") &&
                hasNamedFunctionsForExt(singularName, pluralName, "with") &&
                hasBasicFunctions(singularName, pluralName, "without") &&
                hasNamedFunctionsForExt(singularName, pluralName, "without")
        } else {
            hasBasicFunctions(singularName, pluralName, "has") && hasNamedFunctions(singularName, pluralName)
        }

    private fun KoFunctionProvider.checkForFunctionWithoutName(
        singularName: String,
        pluralName: String,
        isExtension: Boolean,
    ): Boolean =
        if (isExtension) {
            hasBasicFunctions(singularName, pluralName, "with") &&
                hasBasicFunctions(singularName, pluralName, "without")
        } else {
            hasBasicFunctions(singularName, pluralName, "has")
        }
}
