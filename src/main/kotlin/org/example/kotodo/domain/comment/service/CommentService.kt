package org.example.kotodo.domain.comment.service

import org.example.kotodo.domain.comment.dto.CommentDTO
import org.example.kotodo.domain.comment.dto.CommentModifyDTO
import org.example.kotodo.domain.comment.dto.CommentPostDTO

interface CommentService {
    fun getCommentList(todoId: Long): List<CommentDTO>

    fun getComment(todoId: Long, commentId: Long): CommentDTO

    fun postComment(todoId: Long, commentPostDTO: CommentPostDTO): CommentDTO

    fun modifyComment(todoId: Long, commentId: Long, commentModifyDTO: CommentModifyDTO): CommentDTO

    fun deleteComment(todoId: Long, commentId: Long)
}