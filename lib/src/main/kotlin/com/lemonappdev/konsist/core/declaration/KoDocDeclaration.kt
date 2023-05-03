package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag

interface KoDocDeclaration : KoPsiDeclaration {
    val description: String

    val blockTags: List<KoDocTagDeclarationImpl>

    val paramBlockTags: List<KoValuedDocTagDeclarationImpl>

    val returnBlockTag: KoDocTagDeclarationImpl?

    val constructorBlockTag: KoDocTagDeclarationImpl?

    val receiverBlockTag: KoDocTagDeclarationImpl?

    val propertyBlockTags: List<KoValuedDocTagDeclarationImpl>

    val throwsBlockTags: List<KoValuedDocTagDeclarationImpl>

    val exceptionBlockTags: List<KoValuedDocTagDeclarationImpl>

    val sampleBlockTags: List<KoValuedDocTagDeclarationImpl>

    val seeBlockTags: List<KoValuedDocTagDeclarationImpl>

    val authorBlockTags: List<KoDocTagDeclarationImpl>

    val sinceBlockTag: KoDocTagDeclarationImpl?

    val suppressBlockTag: KoDocTagDeclarationImpl?

    val versionBlockTag: KoDocTagDeclarationImpl?

    val propertySetterBlockTag: KoDocTagDeclarationImpl?

    val propertyGetterBlockTag: KoDocTagDeclarationImpl?

    fun hasTags(vararg tags: KoTag): Boolean

    fun parseToValuedBlockTag(koTag: KoTag, sentence: String): KoValuedDocTagDeclarationImpl

    fun parseToBlockTag(koTag: KoTag, sentence: String): KoDocTagDeclarationImpl
}
