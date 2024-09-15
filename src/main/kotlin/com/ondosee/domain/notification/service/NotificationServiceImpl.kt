package com.ondosee.domain.notification.service

import com.ondosee.common.spi.notification.CommandNotificationPort
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val commandNotificationPort: CommandNotificationPort
) : NotificationService {
    override fun saveNotification(deviceToken: String) {
        commandNotificationPort.saveDeviceToken(deviceToken)
    }
}