package com.ondosee.common.spi.notification

interface QueryNotificationPort {
    fun existByDeviceToken(deviceToken: String): Boolean
}