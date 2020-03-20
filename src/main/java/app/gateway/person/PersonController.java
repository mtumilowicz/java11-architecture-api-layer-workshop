package app.gateway.person;

import app.gateway.output.ApiOutput;
import app.gateway.output.ResponseEntityBuilder;
import app.gateway.person.input.NewPersonApiInput;
import app.gateway.person.output.PersonApiOutput;
import app.domain.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<ApiOutput> create(@RequestBody NewPersonApiInput creationInput, UriComponentsBuilder builder) {
        var createResult = creationInput.toDomain().flatMap(x -> personService.create(x));
        return ResponseEntityBuilder.created200(createResult,
                "person",
                PersonApiOutput::from,
                person -> builder.path("persons/{id}").buildAndExpand(person.getId()).toUri()
        );
    }
}
