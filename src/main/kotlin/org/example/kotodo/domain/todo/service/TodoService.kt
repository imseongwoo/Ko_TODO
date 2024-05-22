package org.example.kotodo.domain.todo.service

import org.example.kotodo.domain.todo.dto.TodoCreateDTO
import org.example.kotodo.domain.todo.dto.TodoDTO
import org.example.kotodo.domain.todo.dto.TodoModifyDTO

interface TodoService {
    fun getTodo(todoId: Long): TodoDTO

    fun getTodoList(sortOrder: String?): List<TodoDTO>

    fun modifyTodo(todoId: Long, todoModifyDTO: TodoModifyDTO): TodoDTO

    fun deleteTodo(todoId: Long)

    fun createTodo(createDTO: TodoCreateDTO): TodoDTO
}