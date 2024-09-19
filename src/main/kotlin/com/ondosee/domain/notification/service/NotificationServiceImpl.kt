package com.ondosee.domain.notification.service

import com.ondosee.common.spi.notification.CommandNotificationPort
import com.ondosee.common.spi.notification.QueryNotificationPort
import com.ondosee.domain.notification.presentation.web.req.SetAlarmWebRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class NotificationServiceImpl(
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
}