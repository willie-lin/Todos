package com.example.todo.todos.controller;

import com.example.todo.todos.models.Todo;
import com.example.todo.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: com.example.todo.todos.controller
 * User: YuAn
 * Date: 2018/5/17
 * Time: 16:01
 * Project_name: Todos
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;


    @GetMapping("/todos")
    public List<Todo> getAllTodos(){

        Sort sortbyCreatedAtDesc = new Sort(Sort.Direction.DESC,"createdAt");
        return todoRepository.findAll(sortbyCreatedAtDesc);
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo){
        todo.setCompleted(false);

        return todoRepository.save(todo);

    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Todo>  getTodoById(@PathVariable String id){

        return todoRepository.findById(id).map(todo -> ResponseEntity.ok().body(todo)).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(value = "/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody Todo todo){

        return todoRepository.findById(id).map(todoData ->{
            todoData.setTitle(todo.getTitle());
            todoData.setCompleted(todo.getCompleted());

            Todo updatedTodo = todoRepository.save(todoData);

            return ResponseEntity.ok().body(updatedTodo);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable String id){

        return todoRepository.findById(id).map(
                todo -> {
                    todoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
