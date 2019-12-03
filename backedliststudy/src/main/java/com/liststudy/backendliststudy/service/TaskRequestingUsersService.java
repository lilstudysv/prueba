package com.liststudy.backendliststudy.service;

import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.repository.TaskJpaRepository;
import com.liststudy.backendliststudy.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("taskRequestingUsersService")
public class TaskRequestingUsersService {

    private final TaskJpaRepository taskJpaRepository;
    @Autowired
    public TaskRequestingUsersService(@Qualifier("taskJpaRepository") TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    public Task getTaskById(Long idTask){
        return taskJpaRepository.findById(idTask);
    }

    public boolean isValidRequest(Task task, User requestingUser){
        return task != null
                && !task.getCreator().equals(requestingUser)
                && !task.getRequestUsers().contains(requestingUser)
                && task.getState().equals(EnumStateTask.REQUESTED);
    }

    public void addRequest(Task task, User requestingUser){
        task.getRequestUsers().add(requestingUser);
        taskJpaRepository.save(task);
    }

    public boolean isValidDeleteRequest(Task task, User requestingUser){
        return task != null && task.getRequestUsers().contains(requestingUser);
    }

    public void deleteRequest(Task task, User requestingUser){
        task.getRequestUsers().remove(requestingUser);
        taskJpaRepository.save(task);
    }

    //CONVERTTER TO USERS DTO
}
