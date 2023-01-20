package com.springboot.todoapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @RequestMapping(value = "list-todos")
    public String listTodoPage(ModelMap model) {
        List<ToDo> todos = toDoService.findByUsername("name");
        model.put("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewToDo(ModelMap model) {
        String username = (String) model.get("name");
        ToDo todo = new ToDo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = (String) model.get("name");
        toDoService.addToDo(username, todo.getDescription(),
                LocalDate.now().plusYears(5), false);
        return "redirect:list-todos";
    }
}
