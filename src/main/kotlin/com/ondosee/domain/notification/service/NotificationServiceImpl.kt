package com.ondosee.domain.notification.service

import com.ondosee.common.spi.notification.CommandNotificationPort
import com.ondosee.common.spi.notification.NotificationPort
import com.ondosee.common.spi.notification.QueryNotificationPort
import com.ondosee.domain.notification.domain.entity.NotificationAlarm
import com.ondosee.domain.notification.presentation.web.req.SetAlarmWebRequest
import com.ondosee.thirdparty.firebase.data.enums.NotificationMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
@Transactional(rollbackFor = [Exception::class])
class NotificationServiceImpl(
    private val notificationPort: NotificationPort,
    private val queryNotificationPort: QueryNotificationPort,
    private val commandNotificationPort: CommandNotificationPort
) : NotificationService {
    override fun setAlarm(webRequest: SetAlarmWebRequest) {
        with(webRequest) {
            if (queryNotificationPort.existByDeviceToken(deviceToken)) {
                commandNotificationPort.deleteByDeviceToken(deviceToken)
            }

            commandNotificationPort.saveAlarm(deviceToken, alarmTime)
        }
    }

    override fun sendAlarm() {
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

        val notificationList = queryNotificationPort.findByAlarmTime(currentTime)

        if (notificationList.isNotEmpty()) {
            val notificationAlarm = NotificationAlarm(
                title = NotificationMessage.ALARM_MESSAGE.title,
                body = NotificationMessage.ALARM_MESSAGE.body,
                writer = "ON°C"
            )

            when (notificationList.size) {
                1 -> notificationPort.sendSingleNotification(
                    deviceToken = notificationList.first().deviceToken,
                    notificationAlarm = notificationAlarm
                ) else ->
                    notificationPort.sendMultipleNotification(
                    deviceTokens = notificationList.map { it.deviceToken },
                    notificationAlarm = notificationAlarm
                )
            }
        }
    }
}