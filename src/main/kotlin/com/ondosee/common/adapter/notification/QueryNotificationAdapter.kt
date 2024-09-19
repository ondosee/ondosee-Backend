package com.ondosee.common.adapter.notification

import com.ondosee.common.spi.notification.QueryNotificationPort
import com.ondosee.domain.notification.domain.entity.Notification
import com.ondosee.domain.notification.domain.repository.NotificationRepository
import org.springframework.stereotype.Component

@Component
class QueryNotificationAdapter(
    private val notificationRepository: NotificationRepository
) : QueryNotificationPort {
    override fun findByDeviceToken(deviceToken: String): Notification? {
        return notificationRepository.findByDeviceToken(deviceToken)
    }
}