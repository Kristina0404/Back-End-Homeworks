package de.ait.ec.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(name = "NewCourse",description = "Информация для добавления курса")

public class NewCourseDto {

    @Schema(description = "Название курса",example= "new Course")
    private String title;
    @Schema(description = "Описание курса",example= "description of course")
    private String description;
    @Schema(description = "Начало курса",example= "2023-01-02")
    private String beginDate;
    @Schema(description = "Окончание курса",example= "2023-12-31")
    private String endDate;
    @Schema(description = "Цена за  курс",example= "1000")
    private Double price;


}
