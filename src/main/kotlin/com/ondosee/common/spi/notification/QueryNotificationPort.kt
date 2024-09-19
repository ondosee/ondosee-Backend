package com.ondosee.common.spi.notification

import com.ondosee.domain.notification.domain.entity.Notification

interface QueryNotificationPort {
    fun findByDeviceToken(deviceToken: String): Notification?
}