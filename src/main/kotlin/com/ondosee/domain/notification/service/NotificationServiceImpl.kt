package com.ondosee.domain.notification.service

import com.ondosee.common.spi.notification.CommandNotificationPort
import com.ondosee.common.spi.notification.QueryNotificationPort
import com.ondosee.domain.notification.presentation.web.req.SetAlarmWebRequest
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val queryNotificationPort: QueryNotificationPort,
    private val commandNotificationPort: CommandNotificationPort
) : NotificationService {
    override fun setAlarm(webRequest: SetAlarmWebRequest) {
        with(webRequest) {
            queryNotificationPort.findByDeviceToken(deviceToken)?.let {
                commandNotificationPort.deleteDeviceToken(it)
            }

            commandNotificationPort.saveAlarm(deviceToken, alarmTime)
        }
    }
}