package com.ondosee.common.adapter.notification

import com.ondosee.common.spi.notification.CommandNotificationPort
import com.ondosee.common.spi.notification.QueryNotificationPort
import com.ondosee.domain.notification.domain.entity.Notification
import org.springframework.stereotype.Component

@Component
class CommandNotificationAdapter(
    private val queryNotificationPort: QueryNotificationPort
) : CommandNotificationPort {

    override fun saveDeviceToken(deviceToken: String) {

        val notification = Notification(
            deviceToken = deviceToken,
            alarmTime = null
        )

        queryNotificationPort.saveNotification(notification)
    }
}