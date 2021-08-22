package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Teacher;
import br.com.pedropareschi.collegemanagement.dto.NewTeacherDTO;
import br.com.pedropareschi.collegemanagement.dto.TeacherDTO;
import br.com.pedropareschi.collegemanagement.services.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherResource {

    @Autowired
    private TeacherService service;

    @ApiOperation(value = "Find teacher by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> findById(@PathVariable Integer id) {
        Teacher teacher = service.findById(id);
        return ResponseEntity.ok().body(teacher);
    }

    @ApiOperation(value = "Inserting new teachers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody NewTeacherDTO newTeacherDTO) {
        Teacher teacher = service.fromDTO(newTeacherDTO);
        teacher = service.insert(teacher);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(teacher.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating teacher information")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody TeacherDTO teacherDTO, @PathVariable Integer id) {
        Teacher teacher = service.fromDTO(teacherDTO);
        teacher.setId(id);
        teacher = service.update(teacher);
        return ResponseEntity.noContent().build();
    }
}
