package com.lemon.mango.domain.project.usecase

import org.springframework.stereotype.Service

@Service
class GetNewProjectNameUseCase {
    operator fun invoke(oldName: String): String {
        val words = oldName
            .split(" ")
            .filter { it.isNotBlank() }

        val lastWord = words.last().toIntOrNull()
        val secondToLastWord = words.getOrElse(words.size - 2) { "" }

        return if (words.last() == "copy" && !oldName.endsWith(" ")) {
            "$oldName 2"
        } else if (secondToLastWord == "copy" && lastWord != null) {
            val counter = words.last().toInt()
            val newCounter = (counter + 1)
            oldName.replace(counter.toString(), newCounter.toString())
        } else if (oldName.endsWith(" ")) {
            "${oldName}copy"
        } else {
            "$oldName copy"
        }
    }
}
