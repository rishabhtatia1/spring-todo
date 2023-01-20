package com.springboot.todoapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class ToDoService {
    private static List<ToDo> todoos = new ArrayList<>();
    private static int todosCount = 0;
    static {
        todoos.add(new ToDo(todosCount++, "rishabh", "testing", LocalDate.now().plusYears(1), false));
        todoos.add(new ToDo(todosCount++, "rishabh1", "testing", LocalDate.now().plusYears(2), false));
        todoos.add(new ToDo(todosCount++, "rishabh2", "testing", LocalDate.now().plusYears(-1), false));
    }

    public List<ToDo> findByUsername(String name) {
        return todoos;
    }

    public void addToDo(String username, String description, LocalDate targetDate, Boolean isDone) {
        todoos.add(new ToDo(todosCount++, username, description, targetDate, isDone));
    }

    public void deleteById(int id) {
        // todo.getId() == id
        // todo -> todo.getId() == id
        Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
        todoos.removeIf(predicate);
    }

    public ToDo findById(int id) {
        Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
        ToDo todo = todoos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateToDo(@Valid ToDo todo) {
        deleteById(todo.getId());
        todoos.add(todo);
    }
}
