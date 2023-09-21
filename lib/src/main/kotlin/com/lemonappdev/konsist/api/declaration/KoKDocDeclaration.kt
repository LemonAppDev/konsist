package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoKDocDescriptionProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocAuthorTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocConstructorTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocExceptionTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocParamTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyGetterTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertySetterTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocReceiverTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocReturnTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocSampleTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocSeeTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocSinceTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocSuppressTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocThrowsTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocVersionTagProvider

/**
 * Represents a Kotlin KDoc declaration.
 */
interface KoKDocDeclaration :
    KoBaseDeclaration,
    KoKDocDescriptionProvider,
    KoKDocTagProvider,
    KoTextProvider,
    KoLocationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoKDocAuthorTagProvider,
    KoKDocReceiverTagProvider,
    KoKDocReturnTagProvider,
    KoKDocParamTagProvider,
    KoKDocSeeTagProvider,
    KoKDocSampleTagProvider,
    KoKDocPropertyTagProvider,
    KoKDocPropertyGetterTagProvider,
    KoKDocVersionTagProvider,
    KoKDocThrowsTagProvider,
    KoKDocSuppressTagProvider,
    KoKDocExceptionTagProvider,
    KoKDocPropertySetterTagProvider,
    KoKDocConstructorTagProvider,
    KoKDocSinceTagProvider
