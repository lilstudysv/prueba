package com.liststudy.backendliststudy.task;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
	private final static Long USER_ID = 1l;
	
	@InjectMocks
	private TaskWS taskWS;
	@Mock
	private TaskValidator taskValidator; 
	@Mock
	private TaskService taskService; 
	
	private TaskModel taskModel;

	@Before
	public void init() {
		taskModel = new TaskModel();
		taskModel.setId(1l);
		taskModel.setState(EnumStateTask.REQUESTED);
		taskModel.setTopic(EnumTopicTask.MATHS);
		taskModel.setKind(EnumKindTask.INTERACTIVA);
		taskModel.setPrice(PRICE);
		taskModel.setTitle(TITLE);
		taskModel.setDescription(DESCRIPTION);
		taskModel.setCreator(USER_ID);
	}
	
	@Test
	public void create() {
		when(taskValidator.validateCreateRigth(taskModel)).thenReturn(true);	
		when(taskService.create(taskModel)).thenReturn(taskModel);	
		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<TaskModel>(taskModel, HttpStatus.OK)).matches(taskWS.create(taskModel)));		
	}
	
	@Test
	public void createNoValidate() {
		when(taskValidator.validateCreateRigth(taskModel)).thenReturn(false);	
		Assert.assertTrue(new ReflectionEquals(new ResponseEntity<TaskModel>(HttpStatus.NOT_ACCEPTABLE)).matches(taskWS.create(taskModel)));		
	}
	
	
}
