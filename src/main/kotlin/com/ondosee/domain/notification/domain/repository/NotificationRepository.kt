package com.ondosee.domain.notification.domain.repository

import com.ondosee.domain.notification.domain.entity.Notification
import org.springframework.data.repository.CrudRepository

interface NotificationRepository : CrudRepository<Notification, Long> {
}