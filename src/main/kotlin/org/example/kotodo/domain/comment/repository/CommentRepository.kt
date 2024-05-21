package org.example.kotodo.domain.comment.repository

import org.example.kotodo.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findAllByTodoId(todoId: Long): List<Comment>
}