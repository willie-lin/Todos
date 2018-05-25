package com.example.todo.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Package: com.example.todo.todos.models
 * User: YuAn
 * Date: 2018/5/17
 * Time: 15:38
 * Project_name: Todos
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@Document(collection = "todos")
@JsonIgnoreProperties(value = {"createdAt"},allowGetters = true)
@Data
public class Todo {

    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String title;


    private Boolean completed = false;

    private Date createdAt = new Date();

    public Todo(){
        super();
    }

    public Todo(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
