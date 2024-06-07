package org.example.kotodo.domain.comment.service

import org.example.kotodo.domain.comment.dto.CommentDTO
import org.example.kotodo.domain.comment.dto.CommentModifyDTO
import org.example.kotodo.domain.comment.dto.CommentPostDTO

interface CommentService {
    fun getCommentList(todoId: Long): List<CommentDTO>

    fun getComment(todoId: Long, commentId: Long): CommentDTO

    fun postComment(todoId: Long, commentPostDTO: CommentPostDTO, userId: Long): CommentDTO

    fun modifyComment(todoId: Long, commentId: Long, commentModifyDTO: CommentModifyDTO, userId: Long): CommentDTO

    fun deleteComment(todoId: Long, commentId: Long, userId: Long)
}