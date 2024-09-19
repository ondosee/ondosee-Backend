package com.ondosee.common.adapter.notification

import com.ondosee.common.spi.notification.CommandNotificationPort
import com.ondosee.domain.notification.domain.entity.Notification
import com.ondosee.domain.notification.domain.repository.NotificationRepository
import org.springframework.stereotype.Component

@Component
class CommandNotificationAdapter(
    private val notificationRepository: NotificationRepository
) : CommandNotificationPort {
    override fun deleteDeviceToken(notification: Notification) {
        notificationRepository.delete(notification)
    }

    override fun saveAlarm(deviceToken: String, alarmTime: String) {
        val notification = Notification(
            id = 0L,
            deviceToken = deviceToken,
            alarmTime = alarmTime
        )

        notificationRepository.save(notification)
    }
}