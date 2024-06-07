package org.example.kotodo.domain.todo.service

import org.example.kotodo.domain.todo.dto.TodoCreateDTO
import org.example.kotodo.domain.todo.dto.TodoDTO
import org.example.kotodo.domain.todo.dto.TodoModifyDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TodoService {
    fun getTodo(todoId: Long): TodoDTO

    fun getTodoList(pageable: Pageable, writer: String?): Page<TodoDTO>

    fun modifyTodo(todoId: Long, todoModifyDTO: TodoModifyDTO): TodoDTO

    fun deleteTodo(todoId: Long)

    fun createTodo(createDTO: TodoCreateDTO, userId: Long): TodoDTO
}