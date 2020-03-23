package app.gateway.answers.person;

import app.domain.person.PersonService;
import app.gateway.answers.output.ResponseEntityBuilder;
import app.gateway.answers.person.input.NewPersonApiInput;
import app.gateway.answers.person.output.PersonApiOutput;
import app.gateway.answers.output.ApiOutput;
import app.gateway.answers.person.input.BatchDeletePersonsApiInput;
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
        return ResponseEntityBuilder.okList(personService.findByName(name), "persons", PersonApiOutput::from);
    }

    @PostMapping
    public ResponseEntity<ApiOutput> create(@RequestBody NewPersonApiInput input, UriComponentsBuilder builder) {
        var createResult = input.toDomain().flatMap(personService::create);
        return ResponseEntityBuilder.created(createResult,
                "person",
                PersonApiOutput::from,
                person -> builder.path("persons/{id}").buildAndExpand(person.getId()).toUri()
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiOutput> batchDelete(@RequestBody BatchDeletePersonsApiInput input) {
        var createResult = personService.deleteByIds(input.toDomain());
        return ResponseEntityBuilder.okList(createResult,
                "personIds",
                Function.identity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiOutput> delete(@PathVariable String id) {
        return ResponseEntityBuilder.ok(personService.deleteById(id), "personId", Function.identity());
    }
}
