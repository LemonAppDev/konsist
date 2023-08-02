package com.lemonappdev.konsist.core.provider.packagee

import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore

internal interface KoPackageProviderCore : KoPackageProvider, KoContainingFileProviderCore, KoBaseProviderCore, KoParentProviderCore
