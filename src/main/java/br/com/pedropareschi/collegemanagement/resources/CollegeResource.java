package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.College;
import br.com.pedropareschi.collegemanagement.dto.CollegeDTO;
import br.com.pedropareschi.collegemanagement.dto.NewCollegeDTO;
import br.com.pedropareschi.collegemanagement.services.CollegeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/colleges")
public class CollegeResource {
    
    @Autowired
    private CollegeService service;

    @ApiOperation(value = "Find college by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<College> findById(@PathVariable Integer id){
        College college = service.findById(id);
        return ResponseEntity.ok().body(college);
    }

    @ApiOperation(value = "Inserting new college")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody NewCollegeDTO newCollegeDTO) {
        College college = service.fromNewDTO(newCollegeDTO);
        college = service.insert(college);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(college.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating college information")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CollegeDTO collegeDTO, @PathVariable Integer id) {
        College college = service.fromDTO(collegeDTO);
        college.setId(id);
        college = service.update(college);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "All colleges")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CollegeDTO>> findAll() {
        List<College> list = service.findAll();
        List<CollegeDTO> listDto = list.stream().map(obj -> new CollegeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @ApiOperation(value = "All colleges (per pages)")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CollegeDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<College> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CollegeDTO> listDto = list.map(obj -> new CollegeDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
