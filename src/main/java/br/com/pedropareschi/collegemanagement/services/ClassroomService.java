package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Campus;
import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.Classroom;
import br.com.pedropareschi.collegemanagement.dto.ClassroomDTO;
import br.com.pedropareschi.collegemanagement.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository repository;

    @Autowired
    private ClassService classService;

    public Classroom findById(Integer id){
        return repository.getById(id);
    }

    public Classroom fromDTO(ClassroomDTO classroomDTO, Class aClass){
        Campus campus = new Campus(classroomDTO.getCampusId(), null, null, null);
        return new Classroom(null, campus, classroomDTO.getBuilding(), classroomDTO.getFloor(),
                classroomDTO.getRoom(), DayOfWeek.of(classroomDTO.getDayOfWeek()), classroomDTO.getStart(), classroomDTO.getEnd(), aClass);
    }

    public Classroom fromDTO(ClassroomDTO classroomDTO){
        Classroom classroom = findById(classroomDTO.getId());
        Class aClass = classroom.getaClass();
        return fromDTO(classroomDTO, aClass);
    }

    public void insertAll(List<Classroom> classrooms) {
        repository.saveAll(classrooms);
    }

    public Classroom update(Classroom classroom) {
        Classroom newClassroom = findById(classroom.getId());
        updateData(newClassroom, classroom);
        newClassroom = repository.save(newClassroom);
        return newClassroom;
    }

    private void updateData(Classroom newClassroom, Classroom classroom) {
        newClassroom.setId(classroom.getId());
        newClassroom.setBuilding(classroom.getBuilding());
        newClassroom.setFloor(classroom.getFloor());
        newClassroom.setCampus(classroom.getCampus());
        newClassroom.setRoom(classroom.getRoom());
        newClassroom.setDayOfWeek(classroom.getDayOfWeek());
        newClassroom.setStart(classroom.getStart());
        newClassroom.setEnd(classroom.getEnd());
    }


    public Classroom insert(Classroom classroom) {
        repository.save(classroom);
        classService.update(classroom.getaClass());
        return classroom;
    }
}
