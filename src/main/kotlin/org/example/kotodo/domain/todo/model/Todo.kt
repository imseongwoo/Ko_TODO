package org.example.kotodo.domain.todo.model

import jakarta.persistence.*
import org.example.kotodo.domain.comment.model.Comment
import org.example.kotodo.domain.todo.dto.TodoDTO
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class Todo(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "created_date", nullable = false)
    val createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "writer", nullable = false)
    var writer: String,

    @Column(name = "complete_status", nullable = true)
    var complete: Boolean = false,

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toTodoDTO(): TodoDTO {
        return TodoDTO(
            title = this.title,
            content = this.content,
            writer = this.writer,
            complete = this.complete,
            comments = this.comments.map { it.toCommentDTO() }
        )
    }
}
