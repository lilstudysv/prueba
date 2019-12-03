package com.liststudy.backendliststudy.controller;

import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.security.UserLoggedToken;
import com.liststudy.backendliststudy.service.TaskRequestingUsersService;
import com.liststudy.backendliststudy.service.TaskService;
import com.liststudy.backendliststudy.user.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/{idTask}/requestingUsers")
public class TaskRequestingUsersController {

    private static final Log LOG = LogFactory.getLog(TaskService.class);

    private TaskRequestingUsersService taskRequestingUsersService;
    private UserLoggedToken userLoggedToken;

    @Autowired
    public TaskRequestingUsersController(TaskRequestingUsersService taskRequestingUsersService,
                                         UserLoggedToken userLoggedToken){
        this.taskRequestingUsersService = taskRequestingUsersService;
        this.userLoggedToken = userLoggedToken;
    }

    @PatchMapping
    public ResponseEntity requestTask(@PathVariable(value="idTask") Long idTask) {
        LOG.info("PATCH /tasks/{idTask}/requestingUsers idTask:"+idTask);

        if(idTask == null){
            LOG.info("PATCH /tasks/{idTask}/requestingUsers NOT ACCEPTABLE 1");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        Task task = taskRequestingUsersService.getTaskById(idTask);
        User requestingUser = userLoggedToken.getUserLogged();

        if(taskRequestingUsersService.isValidRequest(task,requestingUser)){
            taskRequestingUsersService.addRequest(task,requestingUser);
            LOG.info("PATCH /tasks/{idTask}/requestingUsers OK ");
            return new ResponseEntity(HttpStatus.OK);
        }else{
            LOG.info("PATCH /tasks/{idTask}/requestingUsers NOT ACCEPTABLE 2");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @DeleteMapping
    public ResponseEntity deleteRequestingUserTask(@PathVariable(value="idTask") Long idTask) {
        LOG.info("DELETE /tasks/{idTask}/requestingUsers idTask:"+idTask);

        if(idTask==null){
            LOG.info("DELETE /tasks/{idTask}/requestingUsers NOT ACCEPTABLE 1");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        Task task = taskRequestingUsersService.getTaskById(idTask);
        User requestingUser = userLoggedToken.getUserLogged();

        if( taskRequestingUsersService.isValidDeleteRequest(task,requestingUser)){
            taskRequestingUsersService.deleteRequest(task,requestingUser);
            LOG.info("DELETE /tasks/{idTask}/requestingUsers OK ");
            return new ResponseEntity(HttpStatus.OK);
        }else{
            LOG.info("DELETE /tasks/{idTask}/requestingUsers NOT ACCEPTABLE 2");
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<UserDTO> getRequestingUsers(){



        return new ResponseEntity(HttpStatus.OK);
    }



}
