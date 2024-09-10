package com.ondosee.common.spi.notification

interface CommandNotificationPort {
    fun saveDeviceToken(deviceToken: String)
}