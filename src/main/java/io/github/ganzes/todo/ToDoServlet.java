package io.github.ganzes.todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todos")
class ToDoServlet {
    private final Logger logger = LoggerFactory.getLogger(ToDoServlet.class);

    private ToDoRepository repository;

    ToDoServlet(ToDoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity <List<ToDo>> findAllTodos(){
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<ToDo> toggleToDo (@PathVariable Integer id){
        Optional<ToDo> toDo = repository.findById(id);
        toDo.ifPresent(t -> {
            t.setDone(!t.isDone());
            repository.save(t);
        });
        return toDo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<ToDo> saveToDo(@RequestBody ToDo toDo) {
        return ResponseEntity.ok(repository.save(toDo));
    }
}

