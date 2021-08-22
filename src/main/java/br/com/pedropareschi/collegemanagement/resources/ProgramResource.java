package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Program;
import br.com.pedropareschi.collegemanagement.dto.ProgramDTO;
import br.com.pedropareschi.collegemanagement.dto.ProgramsByCollegeDTO;
import br.com.pedropareschi.collegemanagement.resources.Utils.URL;
import br.com.pedropareschi.collegemanagement.services.ProgramService;
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

@RestController
@RequestMapping(value = "/programs")
public class ProgramResource {
    @Autowired
    private ProgramService service;

    @ApiOperation(value = "Find program by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Program> findById(@PathVariable Integer id) {
        Program program = service.findById(id);
        return ResponseEntity.ok().body(program);
    }

    @ApiOperation(value = "Inserting new programs")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ProgramDTO programDTO) {
        Program program = service.fromDTO(programDTO);
        program = service.insert(program);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(program.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating program information")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProgramDTO programDTO, @PathVariable Integer id) {
        Program program = service.fromDTO(programDTO);
        program.setId(id);
        program = service.update(program);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Searching programs per college")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProgramsByCollegeDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "colleges", defaultValue = "0") String colleges,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(colleges);
        Page<Program> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProgramsByCollegeDTO> listDto = list.map(ProgramsByCollegeDTO::new);
        return ResponseEntity.ok().body(listDto);
    }
}
