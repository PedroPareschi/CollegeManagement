package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.ProgramCourse;
import br.com.pedropareschi.collegemanagement.dto.CoursesByProgramDTO;
import br.com.pedropareschi.collegemanagement.dto.ProgramCourseDTO;
import br.com.pedropareschi.collegemanagement.resources.Utils.URL;
import br.com.pedropareschi.collegemanagement.services.ProgramCourseService;
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
public class ProgramCourseResource {

    @Autowired
    private ProgramCourseService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @ApiOperation(value = "Inserting courses to programs")
    @RequestMapping(method = RequestMethod.POST, value = "/programs/{program_id}/courses")
    public ResponseEntity<Void> insert(@Valid @RequestBody ProgramCourseDTO programCourseDTO, @PathVariable Integer program_id) {
        ProgramCourse programCourse = service.fromDTO(programCourseDTO, program_id);
        service.insert(programCourse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(programCourse.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Finding courses by programs")
    @RequestMapping(method = RequestMethod.GET, value = "/programs/{program_id}/courses")
    public ResponseEntity<Page<CoursesByProgramDTO>> findPage(
            @PathVariable Integer program_id,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "recommendedTerm") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        Page<ProgramCourse> list = service.search(nameDecoded, program_id, page, linesPerPage, orderBy, direction);
        Page<CoursesByProgramDTO> listDto = list.map(CoursesByProgramDTO::new);
        return ResponseEntity.ok().body(listDto);
    }
}
