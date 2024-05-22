package org.example.kotodo.domain.todo.controller

import org.example.kotodo.domain.todo.dto.TodoCreateDTO
import org.example.kotodo.domain.todo.dto.TodoDTO
import org.example.kotodo.domain.todo.dto.TodoModifyDTO
import org.example.kotodo.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoDTO> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.getTodo(todoId))
    }

    @GetMapping
    fun getTodoList(
        @RequestParam(required = false) sortOrder: String?,
        @RequestParam(required = false) writer: String?
    ): ResponseEntity<List<TodoDTO>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.getTodoList(sortOrder, writer))
    }

    @PostMapping()
    fun crateTodo(@RequestBody todoDTO: TodoCreateDTO): ResponseEntity<TodoDTO> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.createTodo(todoDTO))
    }

    @PutMapping("/{todoId}")
    fun modifyTodo(@PathVariable todoId: Long, @RequestBody todoModifyDTO: TodoModifyDTO)
            : ResponseEntity<TodoDTO> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.modifyTodo(todoId, todoModifyDTO))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build()
    }
}