package io.redeyemedia.todoapi.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun fetch(
        @RequestParam id: Long
    ) : ResponseEntity<User> {
        return userService.findUserById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createOrUpdate(
        @RequestBody user: User
    ) = userService.createOrUpdate(user)

    @DeleteMapping
    fun delete(
        @RequestParam id: Long
    ) = userService.findUserById(id)?.also {
        userService.delete(it)
    }

}