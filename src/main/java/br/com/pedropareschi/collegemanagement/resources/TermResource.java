package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Term;
import br.com.pedropareschi.collegemanagement.dto.TermDTO;
import br.com.pedropareschi.collegemanagement.services.TermService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/terms")
public class TermResource {
    @Autowired
    private TermService service;

    @ApiOperation(value = "Find term by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Term> findById(@PathVariable Integer id){
        Term term = service.findById(id);
        return ResponseEntity.ok().body(term);
    }

    @ApiOperation(value = "New term")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody TermDTO termDTO) {
        Term term = service.fromDTO(termDTO);
        term = service.insert(term);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(term.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Finding all terms to a given student")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Term>> findByStudent(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Term> list = service.findByStudent(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
