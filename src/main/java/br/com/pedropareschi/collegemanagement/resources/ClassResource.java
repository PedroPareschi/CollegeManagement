package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.dto.AlterClassDTO;
import br.com.pedropareschi.collegemanagement.dto.ClassDTO;
import br.com.pedropareschi.collegemanagement.dto.NewClassDTO;
import br.com.pedropareschi.collegemanagement.services.ClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "classes/")
public class ClassResource {
    @Autowired
    private ClassService service;

    @ApiOperation(value = "Finding classes by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Class> findById(@PathVariable Integer id) {
        Class aClass = service.findById(id);
        return ResponseEntity.ok().body(aClass);
    }

    @ApiOperation(value = "Find classes by course")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ClassDTO>> findByCourse(
            @RequestParam(value = "course", defaultValue = "") String course,
            @RequestParam(value = "semester", defaultValue = "") String semester,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Integer courseId = service.parseCourse(course);
        String[] strings = semester.split("/");
        Integer semesterInt = service.parseSemester(strings[0]);
        Integer year = service.parseYear(strings[1]);
        Page<Class> classes = service.findByCourse(courseId, semesterInt, year, page, linesPerPage, orderBy, direction);
        Page<ClassDTO> classDTOS = classes.map(ClassDTO::new);
        return ResponseEntity.ok().body(classDTOS);
    }

    @ApiOperation(value = "Creating a new class")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody NewClassDTO classDTO) {
        Class aClass = service.fromNewDTO(classDTO);
        service.insert(aClass);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(aClass.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating class")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AlterClassDTO classDTO, @PathVariable Integer id) {
        Class aClass = service.fromDTO(classDTO);
        aClass.setId(id);
        aClass = service.update(aClass);
        return ResponseEntity.noContent().build();
    }
}
