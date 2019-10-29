package com.liststudy.backendliststudy.task;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
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
	
	private final TaskJpaRepository taskJpaRepository;
	
	private final UserJpaRepository userJpaRepository;
	
	private final TaskConverter taskConverter;
	
	private final UserRequestConverter userRequestConverter;

	@Autowired
	public TaskService(EntityManagerFactory factory, @Qualifier("taskJpaRepository") TaskJpaRepository taskJpaRepository, @Qualifier("userJpaRepository") UserJpaRepository userJpaRepository, @Qualifier("taskConverter") TaskConverter taskConverter, @Qualifier("userRequestConverter") UserRequestConverter userRequestConverter) {
		if(factory.unwrap(SessionFactory.class) == null){
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.hibernateFactory = factory.unwrap(SessionFactory.class);
		this.taskJpaRepository = taskJpaRepository;
		this.userJpaRepository = userJpaRepository;
		this.taskConverter = taskConverter;
		this.userRequestConverter = userRequestConverter;
	}
	
	public List<TaskModel> getAllTask() {
		
		
		//FILTROS
		
		List<Task> tasks = taskJpaRepository.findAll();
		
		
		List<TaskModel> taskModelList = new ArrayList<>();
		for(Task task :tasks) {
			taskModelList.add(taskConverter.taskToTaskModel(task));
		};
		return taskModelList;
	}
	
	public TaskModel getTaskModel(Long id) {
		return taskConverter.taskToTaskModel(taskJpaRepository.findById(id));
	}
	
	public Task getTask(Long id) {
		return taskJpaRepository.findById(id);
	}
	
	public TaskModel create(TaskModel taskModel) {
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User creador = userJpaRepository.findByUsername(login);
		taskModel.setState(EnumStateTask.REQUESTED);
		taskModel.setCreator(creador.getId());
		Task task = taskConverter.taskModelToTask(taskModel);
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}
	
	
	public TaskModel update(Task task, TaskModel taskModel) {
		task = taskConverter.taskModelToTask(taskModel, task);
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}
	
	public void delete(Task task) {
		taskJpaRepository.delete(task);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////REVISAR////////
	
	
	private SessionFactory hibernateFactory;


	
	
	
	
	
	
	
	public boolean assignTask(Long id) {
		LOG.info("assign task "+id);
		EntityManager em=hibernateFactory.createEntityManager();
		EntityTransaction transaction=em.getTransaction();
		transaction.begin();
		try {
		Task task=em.find(Task.class, id,LockModeType.OPTIMISTIC);
		if(task.getResolver()!=null) {
			LOG.info("cant assign task "+id);
			return false;
		}
		
		String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		task.setResolver(userJpaRepository.findByUsername(username));
		em.persist(task);
		
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
			return new ResponseEntity<TaskInformationModel>(HttpStatus.NOT_ACCEPTABLE);
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
		return new ResponseEntity<TaskInformationModel>(taskInfomationModel, HttpStatus.OK);
	
	}


}
