package org.example.kotodo.domain.comment.model

import jakarta.persistence.*
import org.example.kotodo.domain.comment.dto.CommentDTO
import org.example.kotodo.domain.todo.model.Todo

@Entity
@Table(name = "comment")
class Comment(
    @Column(name = "content")
    var content: String,

    @Column(name = "writer")
    var writer: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun modifyComment(content: String)
    {
        this.content = content
    }

    fun toCommentDTO(): CommentDTO {
        return  CommentDTO(
            id = this.id,
            content = this.content,
            writer = this.writer
        )
    }
}