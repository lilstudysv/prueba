package com.liststudy.backendliststudy.service;

import com.liststudy.backendliststudy.dto.TaskDTO;
import com.liststudy.backendliststudy.service.TaskInputParamsValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskValidatorImplTest {

	//TODO: DONT LIKE THIS CODE -- IF I HAVE 2 entry points i have to test 2 times
	//TODO: WHY I need define so many times taskModelObject --> I Think this can arrive to mistake
	//TODO: Better Define a object in before? and change the value that you need ?
	//TODO: PARAMETRICED TEST isn´t good in this situation.
	private TaskInputParamsValidator taskValidatorImpl =new TaskInputParamsValidator();

	@Test
	public void validateCreateRigth_allRigth(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setPrice(22.2);
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isTrue();
	}

	@Test
	public void validateCreateRigth_priceNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_priceZeroOrLess(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");
		taskDTO.setPrice(0.0);

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_titleNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setDescription("Description");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_titleEmpty(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("");
		taskDTO.setDescription("Description");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_descriptionNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_descriptionEmpty(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setDescription("");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskDTO)).isFalse();
	}


	/*************************************
	 *         VALIDATE_UPDATE_RIGTH     *
	 ************************************/

	@Test
	public void validateUpdateRigth_allRigth(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setPrice(22.2);
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isTrue();
	}


	//TODO: Why  if i delete this method, coverage dont change?
	@Test
	public void validateUpdateRigth_idNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setPrice(22.2);
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_priceNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_priceZeroOrLess(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setTitle("title");
		taskDTO.setDescription("Description");
		taskDTO.setPrice(0.0);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_titleNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setDescription("Description");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_titleEmpty(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setTitle("");
		taskDTO.setDescription("Description");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_descriptionNull(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setTitle("title");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_descriptionEmpty(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);
		taskDTO.setTitle("title");
		taskDTO.setDescription("");
		taskDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskDTO)).isFalse();
	}

	/*************************************
	 *         VALIDATE_DELETE_RIGTH     *
	 ************************************/
	@Test
	public void validateDeleteRigth_allRigth(){
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1L);

		assertThat(taskValidatorImpl.validateDeleteRigth(taskDTO)).isTrue();
	}

	@Test
	public void validateDeleteRigth_idNull(){
		TaskDTO taskDTO = new TaskDTO();

		assertThat(taskValidatorImpl.validateDeleteRigth(taskDTO)).isFalse();
	}

}