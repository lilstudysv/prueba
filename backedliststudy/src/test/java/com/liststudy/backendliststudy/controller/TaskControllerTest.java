package com.liststudy.backendliststudy.controller;

import static org.mockito.Mockito.when;

import com.liststudy.backendliststudy.dto.TaskDTO;
import com.liststudy.backendliststudy.model.EnumKindTask;
import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.EnumTopicTask;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.service.TaskService;
import com.liststudy.backendliststudy.service.TaskInputParamsValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class) 
public class TaskControllerTest {

	private final static Double PRICE = 22.2;
	private final static String TITLE = "title";
	private final static String DESCRIPTION = "Description";
	private final static Long USER_ID = 1L;
	private final static Long TASK_ID=1L;

	@Mock
	private TaskService taskService;
	@Mock
	private TaskInputParamsValidator taskInputParamValidator;

	private TaskController taskController;
	private TaskDTO taskDTOCreate;
	private TaskDTO taskDTOUpdate;
	private TaskDTO taskDTODelete;

	@Before
	public void init() {
		taskController = new TaskController(taskService, taskInputParamValidator);

		taskDTOCreate = new TaskDTO();
		taskDTOCreate.setState(EnumStateTask.REQUESTED);
		taskDTOCreate.setTopic(EnumTopicTask.MATHS);
		taskDTOCreate.setKind(EnumKindTask.INTERACTIVA);
		taskDTOCreate.setPrice(PRICE);
		taskDTOCreate.setTitle(TITLE);
		taskDTOCreate.setDescription(DESCRIPTION);
		taskDTOCreate.setCreator(USER_ID);

		taskDTOUpdate = new TaskDTO();
		taskDTOUpdate.setId(TASK_ID);
		taskDTOUpdate.setState(EnumStateTask.REQUESTED);
		taskDTOUpdate.setTopic(EnumTopicTask.MATHS);
		taskDTOUpdate.setKind(EnumKindTask.INTERACTIVA);
		taskDTOUpdate.setPrice(PRICE);
		taskDTOUpdate.setTitle(TITLE);
		taskDTOUpdate.setDescription(DESCRIPTION);
		taskDTOUpdate.setCreator(USER_ID);

		taskDTODelete = new TaskDTO();
		taskDTODelete.setId(TASK_ID);
		taskDTODelete.setState(EnumStateTask.REQUESTED);
		taskDTODelete.setTopic(EnumTopicTask.MATHS);
		taskDTODelete.setKind(EnumKindTask.INTERACTIVA);
		taskDTODelete.setPrice(PRICE);
		taskDTODelete.setTitle(TITLE);
		taskDTODelete.setDescription(DESCRIPTION);
		taskDTODelete.setCreator(USER_ID);
	}
	
	@Test
	public void create_allRigth() {
		when(taskInputParamValidator.validateCreateRigth(taskDTOCreate)).thenReturn(true);
		when(taskService.create(taskDTOCreate)).thenReturn(taskDTOCreate);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(taskDTOCreate, HttpStatus.OK)).matches(taskController.create(taskDTOCreate)));
	}
	
	@Test
	public void create_inputNotValid() {
		when(taskInputParamValidator.validateCreateRigth(taskDTOCreate)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskController.create(taskDTOCreate)));
	}

	@Test
	public void update_allRigth(){
		Task task = new Task();

		when(taskInputParamValidator.validateUpdateRigth(taskDTOUpdate)).thenReturn(true);
		when(taskService.getTask(taskDTOUpdate.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(true);
		when(taskService.update(task, taskDTOUpdate)).thenReturn(taskDTOUpdate);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(taskDTOUpdate, HttpStatus.OK)).matches(taskController.update(taskDTOUpdate)));
	}

	@Test
	public void update_inputNotValid(){
		when(taskInputParamValidator.validateUpdateRigth(taskDTOUpdate)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskController.update(taskDTOUpdate)));
	}


	@Test
	public void update_TaskNotValid(){
		Task task = new Task();

		when(taskInputParamValidator.validateUpdateRigth(taskDTOUpdate)).thenReturn(true);
		when(taskService.getTask(taskDTOUpdate.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskController.update(taskDTOUpdate)));
	}

	@Test
	public void delete_allRigth(){
		Task task = new Task();

		when(taskInputParamValidator.validateDeleteRigth(taskDTODelete)).thenReturn(true);
		when(taskService.getTask(taskDTODelete.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(true);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.OK)).matches(taskController.delete(taskDTODelete)));
	}

	@Test
	public void delete_inputNotValid(){
		when(taskInputParamValidator.validateDeleteRigth(taskDTODelete)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskController.delete(taskDTODelete)));
	}

	@Test
	public void delete_TaskNotValid(){
		Task task = new Task();

		when(taskInputParamValidator.validateDeleteRigth(taskDTODelete)).thenReturn(true);
		when(taskService.getTask(taskDTOUpdate.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskController.delete(taskDTODelete)));
	}
}
