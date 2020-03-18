package app.api.person;

import app.api.ApiOutput;
import app.api.ResponseEntityBuilder;
import app.api.person.input.PersonCreationApiInput;
import app.api.person.output.PersonApiOutput;
import app.domain.person.PersonCreationInput;
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

    @PostMapping
    public ResponseEntity<ApiOutput> create(@RequestBody PersonCreationApiInput creationInput, UriComponentsBuilder builder) {
        return ResponseEntityBuilder.created200(personService.create(creationInput.toDomain()),
                "person",
                PersonApiOutput::from,
                person -> builder.path("persons/{id}").buildAndExpand(person.getName()).toUri());
    }

}
