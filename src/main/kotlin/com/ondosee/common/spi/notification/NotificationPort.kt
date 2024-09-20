package com.ondosee.common.spi.notification

import com.ondosee.domain.notification.domain.entity.NotificationAlarm

interface NotificationPort {
    fun sendSingleNotification(deviceToken: String, notificationAlarm: NotificationAlarm)
    fun sendMultipleNotification(deviceTokens: List<String>, notificationAlarm: NotificationAlarm)
}