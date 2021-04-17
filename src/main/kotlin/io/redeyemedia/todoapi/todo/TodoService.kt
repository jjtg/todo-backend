package io.redeyemedia.todoapi.todo

import io.redeyemedia.todoapi.user.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TodoService(
    private val repository: TodoRepository
) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(TodoService::class.java)
    }

    fun fetchAll(user: User) = try {
        repository.findAllByUser(user)
    } catch (e: Exception) {
        log.error("Could not fetch todos for user: ${user.id}")
        listOf()
    }

    fun createOrUpdate(todo: Todo) : List<Todo> = try {
        repository.save(todo)
        todo.user?.let { fetchAll(it) }
            ?: listOf()
    } catch (e: Exception) {
        log.error("Failed to create/update new todo for user: ${todo.user?.id} todo id: ${todo.id}", e)
        listOf()
    }

    fun delete(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: Exception) {
            log.error("Failed to delete todo with id: $id", e)
        }
    }

}