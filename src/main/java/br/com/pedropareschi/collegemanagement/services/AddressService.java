package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Address;
import br.com.pedropareschi.collegemanagement.domain.User;
import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.dto.AddressDTO;
import br.com.pedropareschi.collegemanagement.repositories.AddressRepository;
import br.com.pedropareschi.collegemanagement.repositories.UserRepository;
import br.com.pedropareschi.collegemanagement.security.UserSS;
import br.com.pedropareschi.collegemanagement.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserRepository userRepository;


    public Address fromDTO(AddressDTO addressDTO) {
        return new Address(null, addressDTO.getStreet(), addressDTO.getNumber(), addressDTO.getAddressLine2(), addressDTO.getDistrict(), addressDTO.getCep(), addressDTO.getTown(), addressDTO.getState());
    }

    public Address insert(Address address, Integer userId) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Role.ADMIN) && !user.hasRole(Role.COLLEGE_ADMIN) && !userId.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }
        User userRepositoryById = userRepository.getById(userId);
        userRepositoryById.getAddresses().add(address);
        address = repository.save(address);
        userRepository.save(userRepositoryById);
        return address;
    }

    public void delete(Integer userId, Integer addressId) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Role.ADMIN) && !user.hasRole(Role.COLLEGE_ADMIN) && !userId.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }
        repository.deleteById(addressId);
    }
}
