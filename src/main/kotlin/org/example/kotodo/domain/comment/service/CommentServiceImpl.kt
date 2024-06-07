package org.example.kotodo.domain.comment.service

import org.example.kotodo.domain.comment.dto.CommentDTO
import org.example.kotodo.domain.comment.dto.CommentModifyDTO
import org.example.kotodo.domain.comment.dto.CommentPostDTO
import org.example.kotodo.domain.comment.model.Comment
import org.example.kotodo.domain.comment.repository.CommentRepository
import org.example.kotodo.domain.common.exception.InfoNotMatchException
import org.example.kotodo.domain.common.exception.ModelNotFoundException
import org.example.kotodo.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
) : CommentService {
    override fun getCommentList(todoId: Long): List<CommentDTO> {
        return commentRepository.findAllByTodoId(todoId).map { it.toDTO() }
    }

    override fun getComment(todoId: Long, commentId: Long): CommentDTO {
        val comment = getValidComment(commentId)
        isTodoAndCommentAreSame(todoId, comment)
        return comment.toDTO()
    }

    @Transactional
    override fun postComment(todoId: Long, commentPostDTO: CommentPostDTO, userId: Long): CommentDTO {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = Comment(
            content = commentPostDTO.content,
            writer = commentPostDTO.writer,
            password = commentPostDTO.password,
            userId = userId,
            todo = todo
        )
        return commentRepository.save(comment).toDTO()
    }

    @Transactional
    override fun modifyComment(
        todoId: Long,
        commentId: Long,
        commentModifyDTO: CommentModifyDTO,
        userId: Long
    ): CommentDTO {
        val (content, password, writer) = commentModifyDTO
        val comment = getValidComment(commentId)
        isTodoAndCommentAreSame(todoId, comment)
        if (writer != comment.writer || userId != comment.userId) throw InfoNotMatchException("$writer $userId")
        comment.modifyComment(content)
        return commentRepository.save(comment).toDTO()
    }

    @Transactional
    override fun deleteComment(todoId: Long, commentId: Long, userId: Long) {
        val comment = getValidComment(commentId)
        isTodoAndCommentAreSame(todoId, comment)
        if (userId != comment.userId) throw InfoNotMatchException("User ID does not match")
        commentRepository.delete(comment)
    }

    private fun isTodoAndCommentAreSame(todoId: Long, comment: Comment) {
        if (todoId != comment.todo.id)
            throw IllegalStateException("$todoId and ${comment.todo.id} is not matched")
    }

    private fun getValidComment(commentId: Long): Comment {
        return commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
    }

    fun isOwner(commentId: Long, userId: Long): Boolean {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        return comment.userId == userId
    }


    private fun Comment.toDTO(): CommentDTO {
        return CommentDTO(
            id = id,
            content = content,
            writer = writer
        )
    }
}