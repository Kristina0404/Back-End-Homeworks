package de.ait.ec.dto;

import de.ait.ec.models.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Course", description = "Информация о курсе")
public class CourseDto {

    @Schema(description = "id",example = "1")
    private Long id;
    @Schema(description = "Название курса",example= "Course xy")
    private String title;
    @Schema(description = "Описание курса",example= "description of course")
    private String description;
    @Schema(description = "Начало курса",example= "2023-01-02")
    private String beginDate;
    @Schema(description = "Окончание курса",example= "2023-12-31")
    private String endDate;
    @Schema(description = "Цена за  курс",example= "1000")
    private Double price;
    @Schema(description = "state",example = "public")
    private String state;

    public static CourseDto from(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .description(course.getDescription())
                .title(course.getTitle())
                .beginDate(course.getBeginDate().toString())
                .endDate(course.getEndDate().toString())
                .price(course.getPrice())
                .state(course.getState().toString())
                .build();
    }
    public static List<CourseDto> from(List<Course> courses){
        return courses
                .stream()
                .map(CourseDto::from)
                .collect(Collectors.toList());
    }
}
