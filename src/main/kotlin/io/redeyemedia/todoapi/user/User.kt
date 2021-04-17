package io.redeyemedia.todoapi.user

import io.redeyemedia.todoapi.todo.Todo
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue
    val id: Long,
    val firstName: String,
    val lastName: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user")
    val todos: List<Todo> = listOf()
)