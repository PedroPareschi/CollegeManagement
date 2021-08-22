package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.dto.NewStudentDTO;
import br.com.pedropareschi.collegemanagement.dto.StudentDTO;
import br.com.pedropareschi.collegemanagement.dto.StudentExtendedDTO;
import br.com.pedropareschi.collegemanagement.services.StudentService;
import com.itextpdf.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

    @Autowired
    private StudentService service;

    @ApiOperation(value = "Students by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> findById(@PathVariable Integer id){
        Student student = service.findById(id);
        return ResponseEntity.ok().body(student);
    }

    @ApiOperation(value = "Inserting new students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody NewStudentDTO newStudentDTO) {
        Student student = service.fromDTO(newStudentDTO);
        student = service.insert(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating student information")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody StudentDTO studentDTO, @PathVariable Integer id) {
        Student student = service.fromDTO(studentDTO);
        student.setId(id);
        student = service.update(student);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Updating student information that needs authorization")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(value="/{id}/update", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody StudentExtendedDTO studentDTO, @PathVariable Integer id) {
        Student student = service.fromDTO(studentDTO);
        student.setId(id);
        student = service.update(student);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Getting academic records")
    @RequestMapping(value = "/{id}/academic-record", method = RequestMethod.GET)
    public ResponseEntity<Student> getRecordById(@PathVariable Integer id) throws DocumentException, FileNotFoundException, ParseException {
        Student student = service.getRecord(id);
        return ResponseEntity.noContent().build();
    }

}
