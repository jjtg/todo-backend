package io.redeyemedia.todoapi.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(UserService::class.java)
    }

    fun findUserById(id: Long) : User? = try {
        repository.findById(id).orElse(null)
    } catch (e: Exception) {
        log.error("Failed to fetch user with id: $id", e)
        null
    }

    fun createOrUpdate(user: User) : User? = try {
        repository.save(user)
    } catch (e: Exception) {
        log.error("Failed to create user", e)
        null
    }

    fun delete(user: User) = try {
        repository.delete(user)
    } catch (e: Exception) {
        log.error("Failed to delete user with id: ${user.id}", e)
    }

}