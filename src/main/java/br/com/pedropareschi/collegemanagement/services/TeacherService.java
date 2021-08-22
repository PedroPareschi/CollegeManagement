package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Address;
import br.com.pedropareschi.collegemanagement.domain.Teacher;
import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.domain.enums.Sex;
import br.com.pedropareschi.collegemanagement.dto.NewTeacherDTO;
import br.com.pedropareschi.collegemanagement.dto.TeacherDTO;
import br.com.pedropareschi.collegemanagement.repositories.AddressRepository;
import br.com.pedropareschi.collegemanagement.repositories.TeacherRepository;
import br.com.pedropareschi.collegemanagement.security.UserSS;
import br.com.pedropareschi.collegemanagement.services.exceptions.AuthorizationException;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Teacher findById(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Role.ADMIN) && !user.hasRole(Role.COLLEGE_ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }
        Optional<Teacher> teacher = repository.findById(id);
        return teacher.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Teacher.class.getName()
        ));
    }

    public Teacher insert(Teacher teacher) {
        teacher = repository.save(teacher);
        addressRepository.saveAll(teacher.getAddresses());
        return teacher;
    }

    public Teacher fromDTO(NewTeacherDTO newTeacherDTO) {
        Address address = new Address(null, newTeacherDTO.getStreet(), newTeacherDTO.getNumber(), newTeacherDTO.getAddressLine2(),
                newTeacherDTO.getDistrict(), newTeacherDTO.getCep(), newTeacherDTO.getTown(), newTeacherDTO.getState());
        Teacher teacher = new Teacher(null, newTeacherDTO.getName(), newTeacherDTO.getCpf(), newTeacherDTO.getRg(), newTeacherDTO.getEmail(), Sex.toEnum(newTeacherDTO.getSex()), newTeacherDTO.getHasSpecialNeeds()
                , newTeacherDTO.getSpecialNeeds(), newTeacherDTO.getBirthdate(), encoder.encode(newTeacherDTO.getPassword()), newTeacherDTO.getRegistration());
        teacher.addRole(Role.TEACHER);
        teacher.getAddresses().add(address);
        if (newTeacherDTO.getRoles() != null) {
            for (Integer roleId : newTeacherDTO.getRoles()) {
                teacher.addRole(Role.toEnum(roleId));
            }
        }
        return teacher;
    }

    public Teacher fromDTO(TeacherDTO teacherDTO) {
        return new Teacher(null, null, null, null, teacherDTO.getEmail(), null, teacherDTO.getHasSpecialNeeds(), teacherDTO.getSpecialNeeds(), null,
                encoder.encode(teacherDTO.getPassword()), null);
    }

    public Teacher update(Teacher teacher) {
        Teacher newTeacher = findById(teacher.getId());
        updateData(newTeacher, teacher);
        return repository.save(newTeacher);
    }

    private void updateData(Teacher newTeacher, Teacher teacher) {
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setHasSpecialNeeds(teacher.getHasSpecialNeeds());
        newTeacher.setSpecialNeeds(teacher.getSpecialNeeds());
        newTeacher.setPassword(teacher.getPassword());
    }
}




