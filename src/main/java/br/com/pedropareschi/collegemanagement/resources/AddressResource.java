package br.com.pedropareschi.collegemanagement.resources;

import br.com.pedropareschi.collegemanagement.domain.Address;
import br.com.pedropareschi.collegemanagement.dto.AddressDTO;
import br.com.pedropareschi.collegemanagement.services.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AddressResource {

    @Autowired
    private AddressService service;

    @ApiOperation("Adding addresses to students")
    @PostMapping(value = "/students/{student_id}/addresses")
    public ResponseEntity<Void> insert(@Valid @RequestBody AddressDTO addressDTO, @PathVariable Integer student_id) {
        Address address = service.fromDTO(addressDTO);
        service.insert(address, student_id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Deleting students addresses")
    @DeleteMapping(value = "/students/{student_id}/addresses/{address_id}")
    public ResponseEntity<Void> delete(@PathVariable Integer student_id, @PathVariable Integer address_id) {
        service.delete(student_id, address_id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Inserting addresses to teachers")
    @PostMapping(value = "/teachers/{teacher_id}/addresses")
    public ResponseEntity<Void> insertTeacherAddress(@Valid @RequestBody AddressDTO addressDTO, @PathVariable Integer teacher_id) {
        Address address = service.fromDTO(addressDTO);
        service.insert(address, teacher_id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Deleting teachers addresses")
    @DeleteMapping(value = "/teachers/{teacher_id}/addresses/{address_id}")
    public ResponseEntity<Void> deleteTeacherAddress(@PathVariable Integer teacher_id, @PathVariable Integer address_id) {
        service.delete(teacher_id, address_id);
        return ResponseEntity.noContent().build();
    }
}
