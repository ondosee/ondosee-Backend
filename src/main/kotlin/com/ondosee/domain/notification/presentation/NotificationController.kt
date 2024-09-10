package com.ondosee.domain.notification.presentation

import com.ondosee.domain.notification.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationService: NotificationService
) {
    @PostMapping
    fun saveNotification(@RequestParam deviceToken: String): ResponseEntity<Void> =
        notificationService.saveNotification(deviceToken)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}