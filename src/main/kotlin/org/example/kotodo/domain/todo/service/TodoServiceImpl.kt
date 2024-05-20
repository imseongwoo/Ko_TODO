package org.example.kotodo.domain.todo.service

import org.example.kotodo.domain.common.exception.ModelNotFoundException
import org.example.kotodo.domain.todo.dto.TodoCreateDTO
import org.example.kotodo.domain.todo.dto.TodoDTO
import org.example.kotodo.domain.todo.dto.TodoModifyDTO
import org.example.kotodo.domain.todo.model.Todo
import org.example.kotodo.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
) : TodoService {
    override fun getTodo(todoId: Long): TodoDTO {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toDTO()
    }

    override fun getTodoList(): List<TodoDTO> {
        return todoRepository.findAll().map { it.toDTO() }
    }

    @Transactional
    override fun modifyTodo(todoId: Long, todoModifyDTO: TodoModifyDTO): TodoDTO {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (title, content, writer, complete) = todoModifyDTO
        with(todo) {
            this.title = title
            this.content = content
            this.writer = writer
            this.complete = complete
        }
        return todoRepository.save(todo).toDTO()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todoRepository.delete(todo)
    }

    @Transactional
    override fun createTodo(createDTO: TodoCreateDTO): TodoDTO {
        return todoRepository.save(
            Todo(
                title = createDTO.title,
                content = createDTO.content,
                writer = createDTO.writer
            )
        ).toDTO()
    }

    private fun Todo.toDTO(): TodoDTO {
        return TodoDTO(
            title = title,
            content = content,
            writer = writer,
            complete = complete
        )
    }
}