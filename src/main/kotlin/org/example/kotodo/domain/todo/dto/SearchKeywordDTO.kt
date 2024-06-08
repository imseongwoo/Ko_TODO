package org.example.kotodo.domain.todo.dto


data class SearchKeywordDTO(
    val title: String? = null,
    val writer: String? = null,
    val content: String? = null
)