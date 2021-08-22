package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Course;
import br.com.pedropareschi.collegemanagement.dto.CourseDTO;
import br.com.pedropareschi.collegemanagement.services.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/courses")
public class CourseResource {
    @Autowired
    private CourseService service;

    @ApiOperation(value = "Find course by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> findById(@PathVariable Integer id) {
        Course course = service.findById(id);
        return ResponseEntity.ok().body(course);
    }

    @ApiOperation(value = "Inserting new courses")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CourseDTO courseDTO) {
        Course course = service.fromDTO(courseDTO);
        course = service.insert(course);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating course information")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CourseDTO courseDTO, @PathVariable Integer id) {
        Course course = service.fromDTO(courseDTO);
        course.setId(id);
        course = service.update(course);
        return ResponseEntity.noContent().build();
    }
}
