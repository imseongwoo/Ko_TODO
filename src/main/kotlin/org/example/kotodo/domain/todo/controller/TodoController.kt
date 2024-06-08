package org.example.kotodo.domain.todo.controller

import jakarta.validation.Valid
import org.example.kotodo.domain.common.exception.InvalidateDTOError
import org.example.kotodo.domain.todo.dto.SearchKeywordDTO
import org.example.kotodo.domain.todo.dto.TodoCreateDTO
import org.example.kotodo.domain.todo.dto.TodoDTO
import org.example.kotodo.domain.todo.dto.TodoModifyDTO
import org.example.kotodo.domain.todo.service.TodoService
import org.example.kotodo.infra.security.UserPrincipal
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.BindingResult
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
        @PageableDefault(size = 5) pageable: Pageable,
        searchKeywordDTO: SearchKeywordDTO?
    ): ResponseEntity<List<TodoDTO>> {
        val todosPage = todoService.getTodoList(pageable, searchKeywordDTO)
        val todosContent = todosPage.content
        return ResponseEntity.status(HttpStatus.OK)
            .body(todosContent)
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    fun createTodo(
        @Valid @RequestBody todoDTO: TodoCreateDTO,
        bindingResult: BindingResult,
        @AuthenticationPrincipal principal: UserPrincipal
    ): ResponseEntity<TodoDTO> {
        if (bindingResult.hasErrors()) {
            throw InvalidateDTOError("todoCreateDTO", bindingResult.fieldError?.defaultMessage ?: "")
        }
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.createTodo(todoDTO, principal.id))
    }

    @PreAuthorize("@todoServiceImpl.isOwner(#todoId, principal.id)")
    @PutMapping("/{todoId}")
    fun modifyTodo(
        @PathVariable todoId: Long,
        @Valid @RequestBody todoModifyDTO: TodoModifyDTO,
        bindingResult: BindingResult,
        @AuthenticationPrincipal principal: UserPrincipal
    ): ResponseEntity<TodoDTO> {
        if (bindingResult.hasErrors()) {
            throw InvalidateDTOError("todoModifyDTO", bindingResult.fieldError?.defaultMessage ?: "")
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.modifyTodo(todoId, todoModifyDTO))
    }

    @PreAuthorize("@todoServiceImpl.isOwner(#todoId, principal.id)")
    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal principal: UserPrincipal
    ): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build()
    }
}