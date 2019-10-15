package com.liststudy.backendliststudy.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskValidatorTest {

	@InjectMocks
	private TaskValidator taskValidator;

	private TaskModel taskModel;

	@Before
	public void init() {
	taskModel = new TaskModel();
	taskModel.setPrice(22.2);
	taskModel.setTitle("title");
	taskModel.setDescription("Description");
	}

	@Test
	public void validateCreateRigth_allRigth(){
		Assert.assertEquals(true, taskValidator.validateCreateRigth(taskModel));
	}

	@Test
	public void validateCreateRigth_priceNull(){
		taskModel.setPrice(null);
		Assert.assertEquals(false, taskValidator.validateCreateRigth(taskModel));
	}

	@Test
	public void validateCreateRigth_priceZeroOrLess(){
		taskModel.setPrice(0.0);
		Assert.assertEquals(false, taskValidator.validateCreateRigth(taskModel));
	}

	@Test
	public void validateCreateRigth_titleNull(){
		taskModel.setTitle(null);
		Assert.assertEquals(false, taskValidator.validateCreateRigth(taskModel));
	}

	@Test
	public void validateCreateRigth_titleEmpty(){
		taskModel.setTitle("");
		Assert.assertEquals(false, taskValidator.validateCreateRigth(taskModel));
	}

	@Test
	public void validateCreateRigth_descriptionNull(){
		taskModel.setDescription(null);
		Assert.assertEquals(false, taskValidator.validateCreateRigth(taskModel));
	}

	@Test
	public void validateCreateRigth_descriptionEmpty(){
		taskModel.setDescription("");
		Assert.assertEquals(false, taskValidator.validateCreateRigth(taskModel));
	}

 @Test
 public void validateUpdateRigth_allRigth(){

 }

}