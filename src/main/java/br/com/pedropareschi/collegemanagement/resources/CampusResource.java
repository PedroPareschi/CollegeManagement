package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Campus;
import br.com.pedropareschi.collegemanagement.dto.CampusDTO;
import br.com.pedropareschi.collegemanagement.services.CampusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colleges/{college_id}/campus")
public class CampusResource {

    @Autowired
    private CampusService service;

    @ApiOperation(value = "Inserting new campus")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLEGE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CampusDTO campusDTO, @PathVariable Integer college_id) {
        Campus campus = service.fromDTO(campusDTO, college_id);
        campus = service.insert(campus);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(campus.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "All campus by college")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CampusDTO>> findAllByCollege(@PathVariable Integer college_id) {
        List<Campus> list = service.findAll(college_id);
        List<CampusDTO> dtos = list.stream().map(CampusDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }
}
