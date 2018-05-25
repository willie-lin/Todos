package com.example.todo.todos.repository;

import com.example.todo.todos.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Package: com.example.todo.todos.repository
 * User: YuAn
 * Date: 2018/5/17
 * Time: 15:55
 * Project_name: Todos
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {
}
