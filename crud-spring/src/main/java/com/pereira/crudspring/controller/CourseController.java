package com.pereira.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pereira.crudspring.exeptions.DeleteExeption;
import com.pereira.crudspring.model.Course;
import com.pereira.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    @Autowired
    private final CourseRepository courseRepository;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Course find(@PathVariable Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);
       // return ResponseEntity.status(HttpStatus.CREATED).body(
        //    courseRepository.save(course));
    }
    
    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void update(@RequestBody Course course, @PathVariable Long id) {
        Course newCourse = find(course.getId());
        updateData(newCourse, course);
        courseRepository.save(newCourse);
    }

    public void updateData(Course newCourse, Course course) {
		newCourse.setName(course.getName());
		newCourse.setCategoria(course.getCategoria());
	}

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        find(id);
        try {
            courseRepository.deleteById(id);
        } catch (DeleteExeption e) {
            throw new DeleteExeption("Erro na operação de deletar");
        }
    }
    
}
