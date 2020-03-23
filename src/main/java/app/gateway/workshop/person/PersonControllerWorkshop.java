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
        // if found -> ok, if not -> not found, hint: ResponseEntityBuilderWorkshop.okOrNotFound
        // hint: name: person
        return null;
    }

    @GetMapping
    public ResponseEntity<ApiOutputWorkshop> findByName(@RequestParam String name) {
        // if success -> ok, if not -> 400, hint: ResponseEntityBuilderWorkshop.okList
        // hint: name: persons
        return null;
    }

    @PostMapping
    public ResponseEntity<ApiOutputWorkshop> create(@RequestBody NewPersonApiInputWorkshop input, UriComponentsBuilder builder) {
        // if success - created, if not - 400, hint: ResponseEntityBuilderWorkshop.created
        // transform input to domain command and invoke command, hint: toDomain(), personService.create
        // create uri, hint: path, "workshop/persons/{id}", buildAndExpand, person.getId()
        // transform to output, hint: PersonApiOutputWorkshop::from
        // hint: name: person
        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiOutputWorkshop> batchDelete(@RequestBody BatchDeletePersonsApiInputWorkshop input) {
        // if success - ok, if not - 400, hint: ResponseEntityBuilderWorkshop.okList
        // transform input to domain command and invoke command, hint: toDomain(), personService.deleteByIds
        // hint: name: personIds
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiOutputWorkshop> delete(@PathVariable String id) {
        // if success - ok, if not - 400, hint: ResponseEntityBuilderWorkshop.ok
        // hint: name: personId
        return null;
    }
}
