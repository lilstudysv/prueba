package com.liststudy.backendliststudy.task;

import com.liststudy.backendliststudy.user.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskValidatorTest {

	//TODO: DONT LIKE THIS CODE -- IF I HAVE 2 entry points i have to test 2 times
	//TODO: WHY I need define so many times taskModelObject --> I Think this can arrive to mistake
	//TODO: Better Define a object in before? and change the value that you need ?
	//TODO: PARAMETRICED TEST isn´t good in this situation.

	@MockBean
	private  UserJpaRepository userJpaRepository;

	private TaskValidator taskValidator=new TaskValidator(userJpaRepository);

	@Test
	public void validateCreateRigth_allRigth(){
		TaskModel taskModel = new TaskModel();
		taskModel.setPrice(22.2);
		taskModel.setTitle("title");
		taskModel.setDescription("Description");

		assertThat(taskValidator.validateCreateRigth(taskModel)).isTrue();
	}

	@Test
	public void validateCreateRigth_priceNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setTitle("title");
		taskModel.setDescription("Description");

		assertThat(taskValidator.validateCreateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateCreateRigth_priceZeroOrLess(){
		TaskModel taskModel = new TaskModel();
		taskModel.setTitle("title");
		taskModel.setDescription("Description");
		taskModel.setPrice(0.0);

		assertThat(taskValidator.validateCreateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateCreateRigth_titleNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setDescription("Description");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateCreateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateCreateRigth_titleEmpty(){
		TaskModel taskModel = new TaskModel();
		taskModel.setTitle("");
		taskModel.setDescription("Description");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateCreateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateCreateRigth_descriptionNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setTitle("title");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateCreateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateCreateRigth_descriptionEmpty(){
		TaskModel taskModel = new TaskModel();
		taskModel.setTitle("title");
		taskModel.setDescription("");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateCreateRigth(taskModel)).isFalse();
	}


	/*************************************
	 *         VALIDATE_UPDATE_RIGTH     *
	 ************************************/

	@Test
	public void validateUpdateRigth_allRigth(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setPrice(22.2);
		taskModel.setTitle("title");
		taskModel.setDescription("Description");

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isTrue();
	}


	//TODO: Why  if i delete this method, coverage dont change?
	@Test
	public void validateUpdateRigth_idNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setPrice(22.2);
		taskModel.setTitle("title");
		taskModel.setDescription("Description");

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateUpdateRigth_priceNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setTitle("title");
		taskModel.setDescription("Description");

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateUpdateRigth_priceZeroOrLess(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setTitle("title");
		taskModel.setDescription("Description");
		taskModel.setPrice(0.0);

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateUpdateRigth_titleNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setDescription("Description");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateUpdateRigth_titleEmpty(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setTitle("");
		taskModel.setDescription("Description");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateUpdateRigth_descriptionNull(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setTitle("title");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	@Test
	public void validateUpdateRigth_descriptionEmpty(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);
		taskModel.setTitle("title");
		taskModel.setDescription("");
		taskModel.setPrice(22.2);

		assertThat(taskValidator.validateUpdateRigth(taskModel)).isFalse();
	}

	/*************************************
	 *         VALIDATE_DELETE_RIGTH     *
	 ************************************/
	@Test
	public void validateDeleteRigth_allRigth(){
		TaskModel taskModel = new TaskModel();
		taskModel.setId(1L);

		assertThat(taskValidator.validateDeleteRigth(taskModel)).isTrue();
	}

	@Test
	public void validateDeleteRigth_idNull(){
		TaskModel taskModel = new TaskModel();

		assertThat(taskValidator.validateDeleteRigth(taskModel)).isFalse();
	}

}