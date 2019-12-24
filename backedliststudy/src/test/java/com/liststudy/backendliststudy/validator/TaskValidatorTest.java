package com.liststudy.backendliststudy.validator;

import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.security.UserLoggedToken;
import com.liststudy.backendliststudy.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TaskValidatorTest {

    @Mock
    private UserLoggedToken userLoggedToken;

    private TaskValidator taskValidator;

    @Before
    public void init(){
        taskValidator= new TaskValidator(userLoggedToken);
    }

    @Test
    public void isTaskValid_allRigth(){
        //given
        User user = new User();
        user.setId(1L);
        Task task = new Task();
        task.setCreator(user);

        //then
        when(userLoggedToken.getUserLogged()).thenReturn(user);

        //then
        Assert.assertTrue(taskValidator.isTaskValid(task));
    }

    @Test
    public void isTaskValid_taskNull(){
        //given

        //when

        //then
        Assert.assertFalse(taskValidator.isTaskValid(null));
    }


    @Test
    public void isTaskValid_diferentUser(){
        //given
        User user = new User();
        user.setId(1L);
        Task task = new Task();
        task.setCreator(user);
        User userLogged = new User();
        userLogged.setId(2L);

        //when
        when(userLoggedToken.getUserLogged()).thenReturn(userLogged);

        //then
        Assert.assertFalse(taskValidator.isTaskValid(task));
    }

}


