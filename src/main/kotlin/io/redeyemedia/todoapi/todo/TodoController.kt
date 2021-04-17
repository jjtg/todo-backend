package io.redeyemedia.todoapi.todo

import io.redeyemedia.todoapi.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("todo")
class TodoController(
    private val todoService: TodoService,
    private val userService: UserService
) {
    val userId: Long = 1

    @GetMapping
    fun fetchAll(): List<Todo> = userService.findUserById(userId)?.todos ?: listOf()

    @PostMapping
    fun createOrUpdate(
        @RequestBody todo: Todo
    ): List<Todo> = userService.findUserById(userId)?.let {
        todoService.createOrUpdate(Todo(it, todo))
    } ?: listOf()

    @DeleteMapping
    fun delete(
        @RequestParam id: Long
    ): List<Todo> {
        todoService.delete(id)
        return userService.findUserById(userId)?.todos ?: listOf()
    }

}