package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Address;
import br.com.pedropareschi.collegemanagement.domain.Campus;
import br.com.pedropareschi.collegemanagement.domain.College;
import br.com.pedropareschi.collegemanagement.dto.AddressDTO;
import br.com.pedropareschi.collegemanagement.dto.CampusDTO;
import br.com.pedropareschi.collegemanagement.repositories.AddressRepository;
import br.com.pedropareschi.collegemanagement.repositories.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusService {

    @Autowired
    private CampusRepository repository;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private AddressRepository addressRepository;


    public Campus fromDTO(CampusDTO campusDTO, Integer college_id) {
        AddressDTO addressDTO = campusDTO.getAddressDTO();
        Address address = new Address(null, addressDTO.getStreet(), addressDTO.getNumber(), addressDTO.getAddressLine2(), addressDTO.getDistrict(), addressDTO.getCep(), addressDTO.getTown(), addressDTO.getState());
        College college = collegeService.findById(college_id);
        return new Campus(null, campusDTO.getName(), address, college);
    }

    public Campus insert(Campus campus) {
        addressRepository.save(campus.getAddress());
        return repository.save(campus);
    }

    public List<Campus> findAll(Integer college_id) {
        return repository.findCampusesByCollegeId(college_id);
    }
}
