package com.springboot.todoapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
}
