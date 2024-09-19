package com.ondosee.common.spi.notification

import com.ondosee.domain.notification.domain.entity.Notification

interface CommandNotificationPort {
    fun deleteDeviceToken(notification: Notification)
    fun saveAlarm(deviceToken: String, alarmTime: String)
}