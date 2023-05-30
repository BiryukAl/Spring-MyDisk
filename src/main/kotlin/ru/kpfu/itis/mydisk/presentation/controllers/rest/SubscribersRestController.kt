package ru.kpfu.itis.mydisk.presentation.controllers.rest

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.mydisk.data.entity.User
import ru.kpfu.itis.mydisk.domain.services.UserService

@RestController
class SubscribersRestController(
    private val userService: UserService,
) {
    @GetMapping("/user/add/subscribe")
    fun subscribe(
        @RequestParam("myId")
        myId: Long?,
        @RequestParam("idSubscribe")
        idSubscribe: Long?,
    ): ResponseEntity<*> {
        if (myId == null || idSubscribe == null) {
            return ResponseEntity<User>(HttpStatusCode.valueOf(400))
        }
        val user = userService.subscribe(myId, idSubscribe)
            ?: return ResponseEntity<User>(HttpStatusCode.valueOf(403))
        return ResponseEntity<User>(user, HttpStatusCode.valueOf(200))
    }

    @GetMapping("/user/add/unsubscribe")
    fun unsubscribe(
        @RequestParam("myId")
        myId: Long?,
        @RequestParam("idSubscribe")
        idSubscribe: Long?,
    ): ResponseEntity<*> {
        if (myId == null || idSubscribe == null) {
            return ResponseEntity<User>(HttpStatusCode.valueOf(400))
        }
        val user = userService.unsubscribe(myId, idSubscribe)
            ?: return ResponseEntity<User>(HttpStatusCode.valueOf(403))

        return ResponseEntity<User>(HttpStatusCode.valueOf(200))
    }
}