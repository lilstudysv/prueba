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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;

@Service("taskServiceImpl")
public class TaskService  {
	
	@Autowired
	@Qualifier("taskJpaRepository")
	private TaskJpaRepository taskJpaRepository;
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	@Qualifier("taskConverter")
	private TaskConverter taskConverter;
	
	
	private SessionFactory hibernateFactory;

	@Autowired
	public TaskService(EntityManagerFactory factory) {
		if(factory.unwrap(SessionFactory.class) == null){
			throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.hibernateFactory = factory.unwrap(SessionFactory.class);
	}
	
	private static final Log LOG =LogFactory.getLog(TaskService.class);
	
	
	
	
	public List<TaskModel> getAllTask() {
		List<TaskModel> taskModelList = new ArrayList<>();
		for(Task task :taskJpaRepository.findAll()) {
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
	
	
	public TaskModel updateCreateTask(TaskModel taskModel) {
		Long id = taskModel.getId();
		Task taskId=null;
		if(id!=null) {
			taskId=getTask(id);
		}
	
		Task task;
		if(taskId==null) { //CREATE
			String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User creador = userJpaRepository.findByUsername(login);
			taskModel.setState(EnumStateTask.REQUESTED);
			taskModel.setCreator(creador.getId());
			task = taskConverter.taskModelToTask(taskModel);
		}
		else { //UPDATE
			task = taskConverter.taskModelToTask(taskModel, taskId);
			
		}
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}
	
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
	
	
	
	
	
	
	
	
	
	
	
	

}
