package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Course;
import br.com.pedropareschi.collegemanagement.dto.CourseDTO;
import br.com.pedropareschi.collegemanagement.repositories.CourseRepository;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public Course findById(Integer id) {
        Optional<Course> course = repository.findById(id);
        return course.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Course.class.getName()
        ));
    }

    public Course fromDTO(CourseDTO courseDTO) {
        Course course = new Course(null, courseDTO.getName(), courseDTO.getCredits(), courseDTO.getTotalHours(), courseDTO.getSummary(), courseDTO.getMaxAbsencesAllowed(), courseDTO.getMinScoreForApproval());
        if (courseDTO.getPrerequisites() != null) {
            course.setPrerequisites(getPrerequisites(courseDTO));

        }
        return course;
    }

    private List<Course> getPrerequisites(CourseDTO courseDTO) {
        List<Integer> prerequisitesId = courseDTO.getPrerequisites();
        List<Course> prerequisites = new ArrayList<>();
        for (Integer prerequisiteId : prerequisitesId) {
            Course prerequisite = findById(prerequisiteId);
            if (prerequisite != null) {
                prerequisites.add(prerequisite);
            }
        }
        return prerequisites;
    }


    public Course insert(Course course) {
        return repository.save(course);
    }

    public Course update(Course course) {
        Course newCourse = findById(course.getId());
        updateData(newCourse, course);
        return repository.save(newCourse);
    }

    private void updateData(Course newCourse, Course course) {
        newCourse.setName(course.getName());
        newCourse.setCredits(course.getCredits());
        newCourse.setPrograms(course.getPrograms());
        newCourse.setClasses(course.getClasses());
        newCourse.setPrerequisites(course.getPrerequisites());
        newCourse.setMaxAbsencesAllowed(course.getMaxAbsencesAllowed());
        newCourse.setMinScoreForApproval(course.getMinScoreForApproval());
        newCourse.setSummary(course.getSummary());
        newCourse.setTotalHours(course.getTotalHours());
    }

    public List<Course> findByName(String name) {
        return repository.findDistinctByNameContainingIgnoreCase(name);
    }


}
