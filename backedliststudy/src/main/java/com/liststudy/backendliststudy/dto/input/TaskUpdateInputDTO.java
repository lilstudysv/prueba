package com.liststudy.backendliststudy.dto.input;

import com.liststudy.backendliststudy.model.EnumKindTask;
import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.EnumTopicTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@EqualsAndHashCode
public class TaskUpdateInputDTO {

    @NotNull
    private Long id;
    private EnumTopicTask topic;
    private EnumKindTask kind;
    @NotBlank
    @Size(max=100)
    private String title;
    @NotBlank
    @Size(max=3000)
    private String description;
    @NotNull
    @Min(1)
    @Max(3000)
    private Double price;
}
