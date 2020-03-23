package app.gateway.workshop.person;

import app.domain.person.PersonService;
import app.gateway.workshop.output.ApiOutputWorkshop;
import app.gateway.workshop.output.ResponseEntityBuilderWorkshop;
import app.gateway.workshop.person.input.BatchDeletePersonsApiInputWorkshop;
import app.gateway.workshop.person.input.NewPersonApiInputWorkshop;
import app.gateway.workshop.person.output.PersonApiOutputWorkshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Function;

@RestController
@RequestMapping("workshop/persons")
public class PersonControllerWorkshop {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiOutputWorkshop> findById(@PathVariable String id) {
        return ResponseEntityBuilderWorkshop.okOrNotFound(personService.findById(id), "person", PersonApiOutputWorkshop::from);
    }

    @GetMapping
    public ResponseEntity<ApiOutputWorkshop> findByName(@RequestParam String name) {
        return ResponseEntityBuilderWorkshop.okList(personService.findByName(name), "persons", PersonApiOutputWorkshop::from);
    }

    @PostMapping
    public ResponseEntity<ApiOutputWorkshop> create(@RequestBody NewPersonApiInputWorkshop input, UriComponentsBuilder builder) {
        var createResult = input.toDomain().flatMap(x -> personService.create(x));
        return ResponseEntityBuilderWorkshop.created(createResult,
                "person",
                PersonApiOutputWorkshop::from,
                person -> builder.path("workshop/persons/{id}").buildAndExpand(person.getId()).toUri()
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiOutputWorkshop> batchDelete(@RequestBody BatchDeletePersonsApiInputWorkshop input) {
        var createResult = personService.deleteByIds(input.toDomain());
        return ResponseEntityBuilderWorkshop.okList(createResult,
                "personIds",
                Function.identity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiOutputWorkshop> delete(@PathVariable String id) {
        return ResponseEntityBuilderWorkshop.ok(personService.deleteById(id), "personId", Function.identity());
    }
}
