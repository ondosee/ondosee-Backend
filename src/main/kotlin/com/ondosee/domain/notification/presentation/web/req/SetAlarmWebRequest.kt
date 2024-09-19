package com.ondosee.domain.notification.presentation.web.req

import javax.validation.constraints.Pattern

data class SetAlarmWebRequest(
    val deviceToken: String,

    @field:Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    val alarmTime: String
)
