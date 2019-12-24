package com.liststudy.backendliststudy.dto.output;

import com.liststudy.backendliststudy.model.EnumKindTask;
import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.EnumTopicTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TaskOutputDTO {

    private Long id;
    private EnumStateTask state;
    private EnumTopicTask topic;
    private EnumKindTask kind;
    private String title;
    private String description;
    private Double price;
}
