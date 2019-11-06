package com.liststudy.backendliststudy.task;

import org.springframework.stereotype.Service;

@Service("taskInputParamsValidator")
public class TaskInputParamsValidator {

    public boolean validateCreateRigth(TaskModel taskModel) {
		return validatePrice(taskModel.getPrice())
                && validateTitle(taskModel.getTitle())
				&& validateDescription(taskModel.getDescription());
	}

	public boolean validateUpdateRigth(TaskModel taskModel) {
		return validateId(taskModel.getId())
                && validatePrice(taskModel.getPrice())
                && validateTitle(taskModel.getTitle())
				&& validateDescription(taskModel.getDescription())  ;
	}

    public boolean validateDeleteRigth(TaskModel taskModel) {
        return validateId(taskModel.getId());
    }

	private boolean validatePrice(Double price) {return price!=null && price>0; }
	
	private boolean validateTitle(String title) {
		return title!=null && !"".equals(title);
	}
	
	private boolean validateDescription(String description) {
		return description!=null && !"".equals(description);
	}

	private boolean validateId(Long id) { return id!=null; }

}
