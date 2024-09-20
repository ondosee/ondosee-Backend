package com.ondosee.thirdparty.firebase

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.ondosee.common.spi.notification.NotificationPort
import com.ondosee.domain.notification.domain.entity.NotificationAlarm
import org.springframework.context.annotation.Configuration

@Configuration
class FirebaseAdapter : NotificationPort {
    private val firebaseInstance: FirebaseMessaging
        get() = FirebaseMessaging.getInstance()

    override fun sendSingleNotification(deviceToken: String, notificationAlarm: NotificationAlarm) {
        val message = getMassageBuilderByNotification(notificationAlarm)
            .setToken(deviceToken)
            .build()
        firebaseInstance.send(message)
    }

    override fun sendMultipleNotification(deviceTokens: List<String>, notificationAlarm: NotificationAlarm) {
        val message = getMulticastMassageBuilderByNotification(notificationAlarm)
            .addAllTokens(deviceTokens)
            .build()
        firebaseInstance.sendMulticastAsync(message)
    }

    private fun getMassageBuilderByNotification(notificationAlarm: NotificationAlarm) =
        with(notificationAlarm) {
            Message.builder()
                .putData("title", title)
                .putData("body", body)
        }

    private fun getMulticastMassageBuilderByNotification(notificationAlarm: NotificationAlarm) =
        with(notificationAlarm) {
            MulticastMessage.builder()
                .putData("title", title)
                .putData("body", body)
        }
}