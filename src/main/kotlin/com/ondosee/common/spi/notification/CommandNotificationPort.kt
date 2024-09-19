package com.ondosee.common.spi.notification

interface CommandNotificationPort {
    fun deleteByDeviceToken(deviceToken: String)
    fun saveAlarm(deviceToken: String, alarmTime: String)
}