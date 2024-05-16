package org.example.kotodo.domain.todo.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "todo")
data class Todo(
    @Column(name = "title", nullable = false)
    var title: String,
    @Column(name = "content", nullable = false)
    var content: String,
    @Column(name = "created_date", nullable = false)
    var createdDate: Date,
    @Column(name = "writer", nullable = false)
    var writer: String,
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
