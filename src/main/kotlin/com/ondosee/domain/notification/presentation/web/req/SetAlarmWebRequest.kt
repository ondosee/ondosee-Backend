package com.ondosee.domain.notification.presentation.web.req

import org.springframework.web.bind.annotation.RequestParam
import javax.validation.constraints.Pattern

data class SetAlarmWebRequest(
    @RequestParam
    val deviceToken: String,

    @RequestParam
    @field:Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    val alarmTime: String
)
