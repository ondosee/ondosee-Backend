package com.ondosee.domain.notification.presentation

import com.ondosee.domain.notification.presentation.web.req.SetAlarmWebRequest
import com.ondosee.domain.notification.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationService: NotificationService
) {
    @PostMapping
    fun setAlarm(@Valid webRequest: SetAlarmWebRequest): ResponseEntity<Unit> =
        notificationService.setAlarm(webRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}