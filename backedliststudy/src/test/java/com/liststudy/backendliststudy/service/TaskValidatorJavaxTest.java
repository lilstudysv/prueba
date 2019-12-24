package com.liststudy.backendliststudy.service;

import com.liststudy.backendliststudy.dto.input.TaskCreateInputDTO;
import com.liststudy.backendliststudy.validator.TaskValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskValidatorJavaxTest {
    @Test
    public void test(){
        Assert.assertTrue(true);
    }

    /*
	//TODO: DONT LIKE THIS CODE -- IF I HAVE 2 entry points i have to test 2 times
	//TODO: WHY I need define so many times taskModelObject --> I Think this can arrive to mistake
	//TODO: Better Define a object in before? and change the value that you need ?
	//TODO: PARAMETRICED TEST isn´t good in this situation.
	private TaskValidator taskValidatorImpl =new TaskValidator(userLoggedToken);

	@Test
	public void validateCreateRigth_allRigth(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setPrice(22.2);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isTrue();
	}

	@Test
	public void validateCreateRigth_priceNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_priceZeroOrLess(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");
		taskCreateInputDTO.setPrice(0.0);

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_titleNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setDescription("Description");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_titleEmpty(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setTitle("");
		taskCreateInputDTO.setDescription("Description");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_descriptionNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateCreateRigth_descriptionEmpty(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateCreateRigth(taskCreateInputDTO)).isFalse();
	}


	/*************************************
	 *         VALIDATE_UPDATE_RIGTH     *
	 ***********************************

	@Test
	public void validateUpdateRigth_allRigth(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setPrice(22.2);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isTrue();
	}


	//TODO: Why  if i delete this method, coverage dont change?
	@Test
	public void validateUpdateRigth_idNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setPrice(22.2);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_priceNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_priceZeroOrLess(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("Description");
		taskCreateInputDTO.setPrice(0.0);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_titleNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setDescription("Description");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_titleEmpty(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setTitle("");
		taskCreateInputDTO.setDescription("Description");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_descriptionNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	@Test
	public void validateUpdateRigth_descriptionEmpty(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);
		taskCreateInputDTO.setTitle("title");
		taskCreateInputDTO.setDescription("");
		taskCreateInputDTO.setPrice(22.2);

		assertThat(taskValidatorImpl.validateUpdateRigth(taskCreateInputDTO)).isFalse();
	}

	/*************************************
	 *         VALIDATE_DELETE_RIGTH     *
	 ***********************************
	@Test
	public void validateDeleteRigth_allRigth(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();
		taskCreateInputDTO.setId(1L);

		assertThat(taskValidatorImpl.validateDeleteRigth(taskCreateInputDTO)).isTrue();
	}

	@Test
	public void validateDeleteRigth_idNull(){
		TaskCreateInputDTO taskCreateInputDTO = new TaskCreateInputDTO();

		assertThat(taskValidatorImpl.validateDeleteRigth(taskCreateInputDTO)).isFalse();
	}
*/
}