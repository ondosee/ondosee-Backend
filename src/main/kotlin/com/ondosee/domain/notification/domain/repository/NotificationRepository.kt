package com.ondosee.domain.notification.domain.repository

import com.ondosee.domain.notification.domain.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun existsByDeviceToken(deviceToken: String): Boolean
    fun deleteByDeviceToken(deviceToken: String)
}