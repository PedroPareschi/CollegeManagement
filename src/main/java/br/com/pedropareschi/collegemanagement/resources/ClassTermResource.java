package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.ClassTerm;
import br.com.pedropareschi.collegemanagement.domain.ClassTermPK;
import br.com.pedropareschi.collegemanagement.dto.ClassTermDTO;
import br.com.pedropareschi.collegemanagement.dto.NewClassTermDTO;
import br.com.pedropareschi.collegemanagement.services.ClassTermService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ClassTermResource {

    @Autowired
    private ClassTermService service;


    @ApiOperation(value = "Updating students relations to the class (grades, absences, ...)")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/classes/{class_id}/terms/{term_id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClassTermDTO classTermDTO, @PathVariable Integer class_id, @PathVariable Integer term_id){
        ClassTerm classTerm = service.fromDTO(classTermDTO, class_id, term_id);
        classTerm.setId(new ClassTermPK(term_id, class_id));
        classTerm = service.update(classTerm);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Students subscription to classes")
    @RequestMapping(method = RequestMethod.POST, value = "/terms/{term_id}/classes")
    public ResponseEntity<Void> insert(@Valid @RequestBody NewClassTermDTO classTermDTO, @PathVariable Integer term_id) {
        ClassTerm classTerm = service.fromDTO(classTermDTO, term_id);
        service.insert(classTerm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(classTerm.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
