package com.ondosee.domain.notification.domain.entity

import javax.persistence.*

@Entity
@Table(name = "notification")
data class Notification(
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "device_token")
    val deviceToken: String,

    @Column(name = "alarm_time")
    val alarmTime: String?
) {
    constructor() : this(0, "", null)
}