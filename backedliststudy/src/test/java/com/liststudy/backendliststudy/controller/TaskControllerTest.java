package com.liststudy.backendliststudy.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.liststudy.backendliststudy.dto.input.TaskCreateInputDTO;
import com.liststudy.backendliststudy.dto.input.TaskUpdateInputDTO;
import com.liststudy.backendliststudy.dto.output.TaskOutputDTO;
import com.liststudy.backendliststudy.model.EnumKindTask;
import com.liststudy.backendliststudy.model.EnumTopicTask;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.service.TaskService;
import com.liststudy.backendliststudy.validator.TaskValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class) 
public class TaskControllerTest {

	@Mock
	private TaskService taskService;
	@Mock
	private TaskValidator taskValidator;

	private TaskController taskController;


	@Before
	public void init() {
		taskController = new TaskController(taskService, taskValidator);
	}
	
	@Test
	public void create_return200Ok() {
        //given
        TaskCreateInputDTO taskCreateDTOCreateInput = new TaskCreateInputDTO();
        taskCreateDTOCreateInput.setTopic(EnumTopicTask.MATHS);
        taskCreateDTOCreateInput.setKind(EnumKindTask.INTERACTIVA);
        taskCreateDTOCreateInput.setPrice(22.2);
        taskCreateDTOCreateInput.setTitle("title");
        taskCreateDTOCreateInput.setDescription("Description");

        ResponseEntity responseEntity = ResponseEntity.ok(new TaskOutputDTO());

        //when
		when(taskService.create(taskCreateDTOCreateInput)).thenReturn(new TaskOutputDTO());

		//then
		Assert.assertTrue(new ReflectionEquals(responseEntity).matches(taskController.create(taskCreateDTOCreateInput)));
	}
	


	@Test
	public void update_return200Ok(){
		//given
        Task task = new Task();

        TaskUpdateInputDTO taskUpdateInputDTO = new TaskUpdateInputDTO();
        taskUpdateInputDTO.setId(1L);
        taskUpdateInputDTO.setTopic(EnumTopicTask.MATHS);
        taskUpdateInputDTO.setKind(EnumKindTask.INTERACTIVA);
        taskUpdateInputDTO.setPrice(22.2);
        taskUpdateInputDTO.setTitle("title");
        taskUpdateInputDTO.setDescription("Description");

        ResponseEntity responseEntity =ResponseEntity.ok(new TaskOutputDTO());

        //when
		when(taskService.getTask(taskUpdateInputDTO.getId())).thenReturn(task);
		when(taskValidator.isTaskValid(task)).thenReturn(true);
		when(taskService.update(task, taskUpdateInputDTO)).thenReturn(new TaskOutputDTO());

		//then
		Assert.assertTrue(new ReflectionEquals(responseEntity).matches(taskController.update(taskUpdateInputDTO)));
	}


	@Test
	public void update_TaskNotValid(){
        //given
        Task task = new Task();

        TaskUpdateInputDTO taskUpdateInputDTO = new TaskUpdateInputDTO();
        taskUpdateInputDTO.setId(1L);
        taskUpdateInputDTO.setTopic(EnumTopicTask.MATHS);
        taskUpdateInputDTO.setKind(EnumKindTask.INTERACTIVA);
        taskUpdateInputDTO.setPrice(22.2);
        taskUpdateInputDTO.setTitle("title");
        taskUpdateInputDTO.setDescription("Description");

        ResponseEntity responseEntity =ResponseEntity.badRequest().build();

        //when
		when(taskService.getTask(taskUpdateInputDTO.getId())).thenReturn(task);
		when(taskValidator.isTaskValid(task)).thenReturn(false);

		//then
		Assert.assertTrue(new ReflectionEquals(responseEntity).matches(taskController.update(taskUpdateInputDTO)));
	}

	@Test
	public void delete_return200Ok(){
		//given
	    Task task = new Task();
        Long id = 1L;
        ResponseEntity responseEntity = ResponseEntity.ok().build();

        //when
        when(taskService.getTask(id)).thenReturn(task);
		when(taskValidator.isTaskValid(task)).thenReturn(true);

		//then
		Assert.assertTrue(new ReflectionEquals(responseEntity).matches(taskController.delete(id)));
	}

   	@Test
    public void delete_verifyCallDelete(){
        //given
        Task task = new Task();
        Long id = 1L;

        //when
        when(taskService.getTask(id)).thenReturn(task);
        when(taskValidator.isTaskValid(task)).thenReturn(true);

        //then
		taskController.delete(id);
        verify(taskService,times(1)).delete(task);
	}

	@Test
	public void delete_TaskNotValid(){
        //given
        Task task = new Task();
        Long id = 1L;
        ResponseEntity responseEntity = ResponseEntity.badRequest().build();

        //when
		when(taskService.getTask(id)).thenReturn(task);
		when(taskValidator.isTaskValid(task)).thenReturn(false);

		//then
		Assert.assertTrue(new ReflectionEquals(responseEntity).matches(taskController.delete(id)));
	}
}
