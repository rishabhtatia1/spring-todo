package com.springboot.todoapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoControllerJpa {
    private ToDoRepository toDoRepository;

    public ToDoControllerJpa(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @RequestMapping(value = "list-todos")
    public String listTodoPage(ModelMap model) {
        String username = getLoggedinUsername();
        List<ToDo> todos = toDoRepository.findByUsername(username);
        model.put("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewToDo(ModelMap model) {
        String username = getLoggedinUsername();
        ToDo todo = new ToDo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedinUsername();
        todo.setUsername(username);
        toDoRepository.save(todo);
        // toDoService.addToDo(username, todo.getDescription(),
        // todo.getTargetDate(), todo.isDone());
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteToDo(@RequestParam int id) {
        // toDoService.deleteById(id);
        toDoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateToDo(@RequestParam int id, ModelMap model) {
        ToDo todo = toDoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateToDo(ModelMap model, @ModelAttribute("todo") ToDo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = (String) model.get("name");
        todo.setUsername(username);
        toDoRepository.save(todo);
        // toDoService.updateToDo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
