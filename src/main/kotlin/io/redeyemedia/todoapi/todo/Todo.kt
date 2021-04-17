package io.redeyemedia.todoapi.todo

import com.fasterxml.jackson.annotation.JsonIgnore
import io.redeyemedia.todoapi.user.User
import java.util.*
import javax.persistence.*

@Entity
data class Todo(
    @Id
    @GeneratedValue
    val id: Long,
    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var user: User?,
    val message: String,
    val done: Boolean = false,
    val deadline: Date? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
) {
    constructor(user: User, todo: Todo) : this(
        id = todo.id,
        user = user,
        message = todo.message,
        done = todo.done,
        deadline = todo.deadline,
        createdAt = todo.createdAt,
        updatedAt = Date()
    )
}
