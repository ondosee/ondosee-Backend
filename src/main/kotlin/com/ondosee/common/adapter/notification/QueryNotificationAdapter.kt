package com.ondosee.common.adapter.notification

import com.ondosee.common.spi.notification.QueryNotificationPort
import com.ondosee.domain.notification.domain.repository.NotificationRepository
import org.springframework.stereotype.Component

@Component
class QueryNotificationAdapter(
    private val notificationRepository: NotificationRepository
) : QueryNotificationPort {
    override fun existByDeviceToken(deviceToken: String): Boolean {
        return notificationRepository.existsByDeviceToken(deviceToken)
    }
}