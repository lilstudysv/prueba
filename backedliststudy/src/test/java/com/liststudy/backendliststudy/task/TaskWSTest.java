package com.liststudy.backendliststudy.task;

import static org.mockito.Mockito.when;

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
public class TaskWSTest {

	private final static Double PRICE = 22.2;
	private final static String TITLE = "title";
	private final static String DESCRIPTION = "Description";
	private final static Long USER_ID = 1L;
	private final static Long TASK_ID=1L;

	@Mock
	private TaskService taskService;
	@Mock
	private TaskInputParamsValidator taskInputParamValidator;

	private TaskWS taskWS;
	private TaskModel taskModelCreate;
	private TaskModel taskModelUpdate;
	private TaskModel taskModelDelete;

	@Before
	public void init() {
		taskWS = new TaskWS(taskService, taskInputParamValidator);

		taskModelCreate = new TaskModel();
		taskModelCreate.setState(EnumStateTask.REQUESTED);
		taskModelCreate.setTopic(EnumTopicTask.MATHS);
		taskModelCreate.setKind(EnumKindTask.INTERACTIVA);
		taskModelCreate.setPrice(PRICE);
		taskModelCreate.setTitle(TITLE);
		taskModelCreate.setDescription(DESCRIPTION);
		taskModelCreate.setCreator(USER_ID);

		taskModelUpdate = new TaskModel();
		taskModelUpdate.setId(TASK_ID);
		taskModelUpdate.setState(EnumStateTask.REQUESTED);
		taskModelUpdate.setTopic(EnumTopicTask.MATHS);
		taskModelUpdate.setKind(EnumKindTask.INTERACTIVA);
		taskModelUpdate.setPrice(PRICE);
		taskModelUpdate.setTitle(TITLE);
		taskModelUpdate.setDescription(DESCRIPTION);
		taskModelUpdate.setCreator(USER_ID);

		taskModelDelete = new TaskModel();
		taskModelDelete.setId(TASK_ID);
		taskModelDelete.setState(EnumStateTask.REQUESTED);
		taskModelDelete.setTopic(EnumTopicTask.MATHS);
		taskModelDelete.setKind(EnumKindTask.INTERACTIVA);
		taskModelDelete.setPrice(PRICE);
		taskModelDelete.setTitle(TITLE);
		taskModelDelete.setDescription(DESCRIPTION);
		taskModelDelete.setCreator(USER_ID);
	}
	
	@Test
	public void create_allRigth() {
		when(taskInputParamValidator.validateCreateRigth(taskModelCreate)).thenReturn(true);
		when(taskService.create(taskModelCreate)).thenReturn(taskModelCreate);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(taskModelCreate, HttpStatus.OK)).matches(taskWS.create(taskModelCreate)));
	}
	
	@Test
	public void create_inputNotValid() {
		when(taskInputParamValidator.validateCreateRigth(taskModelCreate)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskWS.create(taskModelCreate)));
	}

	@Test
	public void update_allRigth(){
		Task task = new Task();

		when(taskInputParamValidator.validateUpdateRigth(taskModelUpdate)).thenReturn(true);
		when(taskService.getTask(taskModelUpdate.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(true);
		when(taskService.update(task, taskModelUpdate)).thenReturn(taskModelUpdate);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(taskModelUpdate, HttpStatus.OK)).matches(taskWS.update(taskModelUpdate)));
	}

	@Test
	public void update_inputNotValid(){
		when(taskInputParamValidator.validateUpdateRigth(taskModelUpdate)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskWS.update(taskModelUpdate)));
	}


	@Test
	public void update_TaskNotValid(){
		Task task = new Task();

		when(taskInputParamValidator.validateUpdateRigth(taskModelUpdate)).thenReturn(true);
		when(taskService.getTask(taskModelUpdate.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskWS.update(taskModelUpdate)));
	}

	@Test
	public void delete_allRigth(){
		Task task = new Task();

		when(taskInputParamValidator.validateDeleteRigth(taskModelDelete)).thenReturn(true);
		when(taskService.getTask(taskModelDelete.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(true);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.OK)).matches(taskWS.delete(taskModelDelete)));
	}

	@Test
	public void delete_inputNotValid(){
		when(taskInputParamValidator.validateDeleteRigth(taskModelDelete)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskWS.delete(taskModelDelete)));
	}

	@Test
	public void delete_TaskNotValid(){
		Task task = new Task();

		when(taskInputParamValidator.validateDeleteRigth(taskModelDelete)).thenReturn(true);
		when(taskService.getTask(taskModelUpdate.getId())).thenReturn(task);
		when(taskService.isTaskValid(task)).thenReturn(false);

		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)).matches(taskWS.delete(taskModelDelete)));
	}
}
