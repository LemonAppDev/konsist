package com.mango.business.usecase.common

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RequireDateIsNowOrLaterUseCase {
    operator fun invoke(
        newDate: LocalDateTime,
        currentDate: LocalDateTime,
    ) {
        // API should not allow to setting past dates, however, the current date will be different on the server
        // (it will be a bit ahead of the client's date retrieved when the request is created)
        // minusDays(1) is used as a custom time frame to avoid this issue.
        require(newDate > currentDate.minusDays(1)) { "Given date is in the past: $newDate" }
    }
}
