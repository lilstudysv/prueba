package com.liststudy.backendliststudy.task;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskConverterTest {

    private final static Double PRICE = 22.2;
    private final static String TITLE = "title";
    private final static String DESCRIPTION = "Description";
    private final static Long USER_ID = 1l;


    @Mock
    private UserJpaRepository userJpaRepository;

    private TaskConverter taskConverter;

    private Task task;
    private TaskDTO taskDTO;
    private User user;

    @Before
    public void init() {
        taskConverter = new TaskConverter(userJpaRepository);

        task = new Task();
        task.setId(1l);
        task.setState(EnumStateTask.REQUESTED);
        task.setTopic(EnumTopicTask.MATHS);
        task.setKind(EnumKindTask.INTERACTIVA);
        task.setPrice(PRICE);
        task.setTitle(TITLE);
        task.setDescription(DESCRIPTION);

        user = new User();
        user.setId(USER_ID);
        task.setCreator(user);

        taskDTO = new TaskDTO();
        taskDTO.setId(1l);
        taskDTO.setState(EnumStateTask.REQUESTED);
        taskDTO.setTopic(EnumTopicTask.MATHS);
        taskDTO.setKind(EnumKindTask.INTERACTIVA);
        taskDTO.setPrice(PRICE);
        taskDTO.setTitle(TITLE);
        taskDTO.setDescription(DESCRIPTION);
        taskDTO.setCreator(USER_ID);
    }


    @Test
    public void taskModelToTask() {
        when(userJpaRepository.findById(USER_ID)).thenReturn(user);
        Assert.assertTrue(new ReflectionEquals(task).matches(taskConverter.taskModelToTask(taskDTO)));
    }

    @Test
    public void taskModelToTask_2params() {
        Assert.assertTrue(new ReflectionEquals(task).matches(taskConverter.taskModelToTask(taskDTO, task)));
    }

    @Test
    public void taskToTaskModel() {
        Assert.assertTrue(new ReflectionEquals(taskDTO).matches(taskConverter.taskToTaskModel(task)));
    }

}
