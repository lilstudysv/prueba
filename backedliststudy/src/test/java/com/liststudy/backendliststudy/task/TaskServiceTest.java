package com.liststudy.backendliststudy.task;

import com.liststudy.backendliststudy.security.UserLoggedToken;
import com.liststudy.backendliststudy.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskJpaRepository taskJpaRepository;
    @Mock
    private TaskConverter taskConverter;
    @Mock
    private UserLoggedToken userLoggedToken;

    private TaskService taskService;

    private Task task;

    @Before
    public void init(){
        taskService= new TaskService(taskJpaRepository,taskConverter, userLoggedToken);
        task = new Task();
        User user = new User();
        user.setId(1L);
        task.setCreator(user);
    }

    @Test
    public void isTaskValid_allRigth(){
        User user = new User();
        user.setId(1L);

        when(userLoggedToken.getUserLogged()).thenReturn(user);

        Assert.assertTrue(taskService.isTaskValid(task));
    }

    @Test
    public void isTaskValid_taskNull(){
       Assert.assertFalse(taskService.isTaskValid(null));
    }


    @Test
    public void isTaskValid_diferentUser(){
        User user = new User();
        user.setId(2L);

        when(userLoggedToken.getUserLogged()).thenReturn(user);

        Assert.assertFalse(taskService.isTaskValid(task));
    }

}


