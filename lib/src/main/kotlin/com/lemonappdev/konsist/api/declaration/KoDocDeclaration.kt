package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoDocTag

interface KoDocDeclaration : KoPsiDeclaration {
    val description: String

    val tags: List<KoDocTagDeclaration>

    val paramTags: List<KoValuedDocTagDeclaration>

    val returnTag: KoDocTagDeclaration?

    val constructorTag: KoDocTagDeclaration?

    val receiverTag: KoDocTagDeclaration?

    val propertyTags: List<KoValuedDocTagDeclaration>

    val throwsTags: List<KoValuedDocTagDeclaration>

    val exceptionTags: List<KoValuedDocTagDeclaration>

    val sampleTags: List<KoValuedDocTagDeclaration>

    val seeTags: List<KoValuedDocTagDeclaration>

    val authorTags: List<KoDocTagDeclaration>

    val sinceTag: KoDocTagDeclaration?

    val suppressTag: KoDocTagDeclaration?

    val versionTag: KoDocTagDeclaration?

    val propertySetterTag: KoDocTagDeclaration?

    val propertyGetterTag: KoDocTagDeclaration?

    fun hasTags(vararg tags: KoDocTag): Boolean

    fun parseToValuedTag(koDocTag: KoDocTag, sentence: String): KoValuedDocTagDeclaration

    fun parseToTag(koDocTag: KoDocTag, sentence: String): KoDocTagDeclaration
}
