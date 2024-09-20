package com.ondosee.domain.notification.scheduler

import com.ondosee.domain.notification.service.NotificationService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class NotificationScheduler(
    private val notificationService: NotificationService
) {
    @Scheduled(cron = "0 * * * * *")
    fun sendNotificationAlarm() {
        notificationService.sendAlarm()
    }
}