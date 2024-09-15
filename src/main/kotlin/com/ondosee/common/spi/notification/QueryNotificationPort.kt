package com.ondosee.common.spi.notification

import com.ondosee.domain.notification.domain.entity.Notification

interface QueryNotificationPort {
    fun saveNotification(notification: Notification): Notification
}