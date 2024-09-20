package com.ondosee.domain.notification.service

import com.ondosee.domain.notification.presentation.web.req.SetAlarmWebRequest

interface NotificationService {
    fun setAlarm(webRequest: SetAlarmWebRequest)
    fun sendAlarm()
}