package com.liststudy.backendliststudy.task;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.user.UserJpaRepository;

@Service("taskServiceImpl")
public class TaskServiceImpl  {
	
	@Autowired
	@Qualifier("taskJpaRepository")
	private TaskJpaRepository taskJpaRepository;
	
	@Autowired
	@Qualifier("taskConverter")
	private TaskConverter taskConverter;
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	
	private SessionFactory hibernateFactory;

	@Autowired
	public TaskServiceImpl(EntityManagerFactory factory) {
		if(factory.unwrap(SessionFactory.class) == null){
			throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.hibernateFactory = factory.unwrap(SessionFactory.class);
	}
	
	
	public List<TaskModel> getAllTask() {
		List<TaskModel> taskModelList = new ArrayList<>();
		for(Task task :taskJpaRepository.findAll()) {
			taskModelList.add(taskConverter.taskToTaskModel(task));
		};
		return taskModelList;
	}
	
	public TaskModel getTask(Long id) {
		return taskConverter.taskToTaskModel(taskJpaRepository.findById(id));
	}
	
	public void updateCreateTask(TaskModel taskModel) {
		Task task2=taskJpaRepository.findById(taskModel.getId());

		Task task=taskConverter.taskModelToTask(taskModel);
		if(task2!=null) {
			task.setVersion(task2.getVersion());
		}
		
		taskJpaRepository.save(task);
	}
	
	public boolean assignTask(Long id) {
		EntityManager em=hibernateFactory.createEntityManager();
		EntityTransaction transaction=em.getTransaction();
		transaction.begin();
		try {
		Task task=em.find(Task.class, id,LockModeType.OPTIMISTIC);
		if(task.getResolver()!=null) {
			return false;
		}
		
		String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		task.setResolver(userJpaRepository.findByUsername(username));
		em.persist(task);
		
		transaction.commit();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	

}
