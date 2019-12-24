package com.liststudy.backendliststudy.dto.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class TaskDeleteInputDTO implements Serializable {

    private Long id;
}
