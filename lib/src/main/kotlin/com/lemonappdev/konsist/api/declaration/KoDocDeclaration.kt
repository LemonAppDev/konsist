package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoTag

interface KoDocDeclaration : KoPsiDeclaration {
    val description: String

    val blockTags: List<KoDocTagDeclaration>

    val paramBlockTags: List<KoValuedDocTagDeclaration>

    val returnBlockTag: KoDocTagDeclaration?

    val constructorBlockTag: KoDocTagDeclaration?

    val receiverBlockTag: KoDocTagDeclaration?

    val propertyBlockTags: List<KoValuedDocTagDeclaration>

    val throwsBlockTags: List<KoValuedDocTagDeclaration>

    val exceptionBlockTags: List<KoValuedDocTagDeclaration>

    val sampleBlockTags: List<KoValuedDocTagDeclaration>

    val seeBlockTags: List<KoValuedDocTagDeclaration>

    val authorBlockTags: List<KoDocTagDeclaration>

    val sinceBlockTag: KoDocTagDeclaration?

    val suppressBlockTag: KoDocTagDeclaration?

    val versionBlockTag: KoDocTagDeclaration?

    val propertySetterBlockTag: KoDocTagDeclaration?

    val propertyGetterBlockTag: KoDocTagDeclaration?

    fun hasTags(vararg tags: KoTag): Boolean

    fun parseToValuedBlockTag(koTag: KoTag, sentence: String): KoValuedDocTagDeclaration

    fun parseToBlockTag(koTag: KoTag, sentence: String): KoDocTagDeclaration
}
