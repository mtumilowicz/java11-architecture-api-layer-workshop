package app.gateway.person;

import app.domain.person.PersonService;
import app.gateway.output.ApiOutput;
import app.gateway.output.ResponseEntityBuilder;
import app.gateway.person.input.BatchDeleteApiInput;
import app.gateway.person.input.NewPersonApiInput;
import app.gateway.person.output.PersonApiOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Function;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiOutput> findById(@PathVariable String id) {
        return ResponseEntityBuilder.okOrNotFound(personService.findById(id), "person", PersonApiOutput::from);
    }

    @GetMapping
    public ResponseEntity<ApiOutput> findByName(@RequestParam String name) {
        return ResponseEntityBuilder.list200(personService.findByName(name), "persons", PersonApiOutput::from);
    }

    @PostMapping
    public ResponseEntity<ApiOutput> create(@RequestBody NewPersonApiInput input, UriComponentsBuilder builder) {
        var createResult = input.toDomain().flatMap(x -> personService.create(x));
        return ResponseEntityBuilder.created(createResult,
                "person",
                PersonApiOutput::from,
                person -> builder.path("persons/{id}").buildAndExpand(person.getId()).toUri()
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiOutput> batchDelete(@RequestBody BatchDeleteApiInput input) {
        var createResult = personService.deleteByIds(input.toDomain());
        return ResponseEntityBuilder.list200(createResult,
                "ids",
                Function.identity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiOutput> delete(@PathVariable String id) {
        return ResponseEntityBuilder.ok(personService.deleteById(id), "personId", Function.identity());
    }
}
