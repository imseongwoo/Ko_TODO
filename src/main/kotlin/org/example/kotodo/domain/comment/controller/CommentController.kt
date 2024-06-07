package org.example.kotodo.domain.comment.controller

import org.example.kotodo.domain.comment.dto.CommentDTO
import org.example.kotodo.domain.comment.dto.CommentModifyDTO
import org.example.kotodo.domain.comment.dto.CommentPostDTO
import org.example.kotodo.domain.comment.service.CommentService
import org.example.kotodo.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos/{todoId}/comments")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping()
    fun getCommentList(@PathVariable todoId: Long): ResponseEntity<List<CommentDTO>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.getCommentList(todoId))
    }

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable todoId: Long, @PathVariable commentId: Long): ResponseEntity<CommentDTO>{
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.getComment(todoId, commentId))
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    fun postComment(
        @PathVariable todoId: Long,
        @RequestBody commentPostDTO: CommentPostDTO,
        @AuthenticationPrincipal principal: UserPrincipal
    ): ResponseEntity<CommentDTO> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(commentService.postComment(todoId, commentPostDTO, principal.id))
    }

    @PreAuthorize("@commentServiceImpl.isOwner(#commentId, principal.id)")
    @PutMapping("/{commentId}")
    fun modifyComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentModifyDTO: CommentModifyDTO,
        @AuthenticationPrincipal principal: UserPrincipal
    ): ResponseEntity<CommentDTO> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.modifyComment(todoId, commentId, commentModifyDTO, principal.id))
    }

    @PreAuthorize("@commentServiceImpl.isOwner(#commentId, principal.id)")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal principal: UserPrincipal
    ): ResponseEntity<Unit> {
        commentService.deleteComment(todoId, commentId, principal.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}