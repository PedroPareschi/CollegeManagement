package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.Classroom;
import br.com.pedropareschi.collegemanagement.domain.Course;
import br.com.pedropareschi.collegemanagement.domain.Teacher;
import br.com.pedropareschi.collegemanagement.domain.enums.Semester;
import br.com.pedropareschi.collegemanagement.domain.enums.Shift;
import br.com.pedropareschi.collegemanagement.dto.AlterClassDTO;
import br.com.pedropareschi.collegemanagement.dto.ClassroomDTO;
import br.com.pedropareschi.collegemanagement.dto.NewClassDTO;
import br.com.pedropareschi.collegemanagement.repositories.ClassRepository;
import br.com.pedropareschi.collegemanagement.repositories.CourseRepository;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassroomService classroomService;

    public Class findById(Integer id) {
        Optional<Class> aClass = repository.findById(id);
        return aClass.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Class.class.getName()
        ));
    }

    public Page<Class> findByCourse(Integer courseId, Integer semester, Integer year, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Course course = courseRepository.getById(courseId);
        return repository.findClassesByCourseAndSemesterAndYear(course, semester, year, pageRequest);
    }

    public Class fromDTO(AlterClassDTO classDTO) {
        Course course = new Course(classDTO.getCourseId(), null, null, null, null, null, null);
        Teacher teacher = new Teacher(classDTO.getTeacherId(), null, null, null, null, null, null, null, null, null, null);
        Integer semester = Semester.getInstance();
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        return new Class(null, Shift.toEnum(classDTO.getShift()), Semester.toEnum(semester), year, classDTO.getSlots(), course, teacher);
    }

    public Class fromNewDTO(NewClassDTO classDTO) {
        Class aClass = fromDTO(classDTO);
        for (ClassroomDTO classroomDTO : classDTO.getClassrooms()) {
            Classroom classroom = classroomService.fromDTO(classroomDTO, aClass);
            aClass.getClassrooms().add(classroom);
        }
        return aClass;
    }

    @Transactional
    public Class insert(Class aClass) {
        aClass.setId(null);
        aClass = repository.save(aClass);
        classroomService.insertAll(aClass.getClassrooms());
        return aClass;
    }


    public Class update(Class aClass) {
        Class newClass = findById(aClass.getId());
        updateData(newClass, aClass);
        newClass = repository.save(newClass);
        return newClass;
    }

    private void updateData(Class newClass, Class aClass) {
        newClass.setClassrooms(aClass.getClassrooms());
        newClass.setSlots(aClass.getSlots());
        newClass.setTeacher(aClass.getTeacher());
        newClass.setCourse(aClass.getCourse());
        newClass.setShift(aClass.getShift());
    }


    public Integer parseCourse(String course) {
        return Integer.parseInt(course);
    }

    public Integer parseSemester(String semester) {
        if (semester.isEmpty()) {
            return Semester.getInstance();
        }
        return Integer.parseInt(semester);
    }

    public Integer parseYear(String year) {
        if (year.isEmpty()) {
            return Calendar.getInstance().get(Calendar.YEAR);
        }
        return Integer.parseInt(year);
    }

}
