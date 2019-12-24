package com.liststudy.backendliststudy.mapper;

import com.liststudy.backendliststudy.dto.input.TaskCreateInputDTO;
import com.liststudy.backendliststudy.dto.input.TaskUpdateInputDTO;
import com.liststudy.backendliststudy.dto.output.TaskOutputDTO;
import com.liststudy.backendliststudy.model.Task;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskOutputDTO taskToTaskOutputDTO(Task task);

    @IterableMapping(nullValueMappingStrategy= NullValueMappingStrategy.RETURN_DEFAULT)
    List<TaskOutputDTO> taskToTaskOutputDTO(List<Task> task);

    Task taskCreateImputDTOtoTask(TaskCreateInputDTO taskCreateInputDTO);

    Task taskUpdateImputDTOtoTask(TaskUpdateInputDTO taskOutputDTO, @MappingTarget Task task);

}
