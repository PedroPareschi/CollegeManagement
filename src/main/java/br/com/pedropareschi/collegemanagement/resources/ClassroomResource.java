package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.Classroom;
import br.com.pedropareschi.collegemanagement.dto.ClassroomDTO;
import br.com.pedropareschi.collegemanagement.services.ClassService;
import br.com.pedropareschi.collegemanagement.services.ClassroomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ClassroomResource {

    @Autowired
    private ClassroomService service;

    @Autowired
    private ClassService classService;

    @ApiOperation(value = "Updating classroom")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/classrooms/{classroom_id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClassroomDTO classroomDTO, @PathVariable Integer classroom_id){
        Classroom classroom = service.fromDTO(classroomDTO);
        classroom.setId(classroom_id);
        classroom = service.update(classroom);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Creating classroom")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/classes/{class_id}/classrooms")
    public ResponseEntity<Void> insert(@Valid @RequestBody ClassroomDTO classroomDTO, @PathVariable Integer class_id) {
        Class aClass = classService.findById(class_id);
        Classroom classroom = service.fromDTO(classroomDTO, aClass);
        service.insert(classroom);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(classroom.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
