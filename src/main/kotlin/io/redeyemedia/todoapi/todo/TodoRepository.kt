package io.redeyemedia.todoapi.todo

import io.redeyemedia.todoapi.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByUser(user: User): List<Todo>
}