package com.liststudy.backendliststudy.task;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.liststudy.backendliststudy.security.UserLoggedToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.taskrequest.UserRequestConverter;
import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;

@Service("taskService")
public class TaskService  {
	
	private static final Log LOG =LogFactory.getLog(TaskService.class);

	@PersistenceContext
	private EntityManager entityManager;

	private final TaskJpaRepository taskJpaRepository;
	private final TaskConverter taskConverter;
	private final UserLoggedToken userLoggedToken;
	//TODO: ELIMINAR LAS 2 siguientes
	private final UserJpaRepository userJpaRepository;
	private final UserRequestConverter userRequestConverter;

	@Autowired
	public TaskService(@Qualifier("taskJpaRepository") TaskJpaRepository taskJpaRepository,
					   @Qualifier("taskConverter") TaskConverter taskConverter,
					   @Qualifier("userLoggedToken") UserLoggedToken userLoggedToken,
					   @Qualifier("userJpaRepository") UserJpaRepository userJpaRepository,
					   @Qualifier("userRequestConverter") UserRequestConverter userRequestConverter) {

		this.taskJpaRepository = taskJpaRepository;
		this.taskConverter = taskConverter;
		this.userLoggedToken = userLoggedToken;
		this.userJpaRepository = userJpaRepository;
		this.userRequestConverter = userRequestConverter;
	}
	
	public List<TaskModel> getAllTask() {
		List<Task> tasks = taskJpaRepository.getTasksFilters();
		List<TaskModel> taskModelList = new ArrayList<>();
		tasks.forEach(task -> taskModelList.add(taskConverter.taskToTaskModel(task)));
		return taskModelList;
	}
	
	public TaskModel getTaskModel(Long id) {
		return taskConverter.taskToTaskModel(taskJpaRepository.findById(id));
	}
	
	public Task getTask(Long id) {
		return taskJpaRepository.findById(id);
	}

	public Boolean isTaskValid(Task task){
		return task!=null && task.getCreator().getId().equals(userLoggedToken.getUserLogged().getId());
	}

	public TaskModel create(TaskModel taskModel) {
		taskModel.setState(EnumStateTask.REQUESTED);
		taskModel.setCreator(userLoggedToken.getUserLogged().getId());
		Task task = taskConverter.taskModelToTask(taskModel);
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}
	
	public TaskModel update(Task task, TaskModel taskModel) {
		task = taskConverter.taskModelToTask(taskModel, task);
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}

	//TODO:TESTING VERIFY????????
	public void delete(Task task) {
		taskJpaRepository.delete(task);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	













	
	
	





	

	public boolean assignTask(Long id) {
		LOG.info("assign task "+id);
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();
		try {
		Task task=entityManager.find(Task.class, id,LockModeType.OPTIMISTIC);
		if(task.getResolver()!=null) {
			LOG.info("cant assign task "+id);
			return false;
		}
		
		String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		task.setResolver(userJpaRepository.findByUsername(username));
		entityManager.persist(task);
		
		transaction.commit();
		}catch(Exception e) {
			LOG.info("cant assign task rollback "+id);
			return false;
		}
		return true;
	}

	public ResponseEntity<TaskInformationModel> obtainInformationTask(Long idTask) {
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogueado = userJpaRepository.findByUsername(login);
		
		//NO CUMPLE LOS REQUESITOS EL USUARIO
		
		//NO CUMPLE LOS REQUISITOS LA TAREA
		Task task = taskJpaRepository.findById(idTask);
		if(task==null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		TaskInformationModel taskInfomationModel = new TaskInformationModel();
		
		if(task.getCreator().getId().equals(userLogueado.getId())) {//CREADOR
			taskInfomationModel.setCreator(true);
			taskInfomationModel.setRequestUsers(userRequestConverter.listUsersToListUserRequest(task.getRequestUsers()));
			taskInfomationModel.setAcceptedUsers(userRequestConverter.listUsersToListUserRequest(task.getAcceptedUsers()));
		}else {
			if(task.getAcceptedUsers().contains(userLogueado)) {
				taskInfomationModel.setRequestAvaible(false);
				taskInfomationModel.setStartProgessAvaible(true);
			}else if(task.getRequestUsers().contains(userLogueado)) {
				taskInfomationModel.setRequestAvaible(true);
				taskInfomationModel.setStartProgessAvaible(false);
			}else {
				taskInfomationModel.setRequestAvaible(false);
				taskInfomationModel.setStartProgessAvaible(false);
			}
				
		}
		return new ResponseEntity<>(taskInfomationModel, HttpStatus.OK);
	
	}


}
