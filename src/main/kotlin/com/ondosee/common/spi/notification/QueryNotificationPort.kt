package com.ondosee.common.spi.notification

import com.ondosee.domain.notification.domain.entity.Notification

interface QueryNotificationPort {
    fun existByDeviceToken(deviceToken: String): Boolean
    fun findByAlarmTime(alarmTime: String): List<Notification>
}