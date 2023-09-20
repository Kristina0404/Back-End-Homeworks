package de.ait.ec.controlers;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.services.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = @Tag(name = "Courses"))
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CoursesService coursesService;
    @Operation(summary = "Добавление курса",description = "Доступно админестратору системы")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto addCourse(@RequestBody NewCourseDto newCourse){
    return coursesService.addCourses(newCourse);
    }

    @Operation(summary = "Получение списка курсов",description = "Доступно всем пользователям")
    @GetMapping
    public List<CourseDto> getCourses(){
        return coursesService.getCourses();

    }
}
