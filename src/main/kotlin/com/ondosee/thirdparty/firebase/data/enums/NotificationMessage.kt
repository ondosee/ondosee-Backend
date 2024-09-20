package com.ondosee.thirdparty.firebase.data.enums

enum class NotificationMessage(
    val title: String,
    val body: String
) {
    ALARM_MESSAGE(
        title = "오늘의 날씨 특이사항은??",
        body = "ON°C 가 설정하신 시간에 알림을 보냈어요 :)"
    )
}