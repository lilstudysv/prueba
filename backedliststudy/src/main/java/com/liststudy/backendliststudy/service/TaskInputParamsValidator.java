package com.liststudy.backendliststudy.service;

import com.liststudy.backendliststudy.dto.TaskDTO;
import org.springframework.stereotype.Service;

@Service("taskInputParamsValidator")
public class TaskInputParamsValidator {

    public boolean validateCreateRigth(TaskDTO taskDTO) {
		return validatePrice(taskDTO.getPrice())
                && validateTitle(taskDTO.getTitle())
				&& validateDescription(taskDTO.getDescription());
	}

	public boolean validateUpdateRigth(TaskDTO taskDTO) {
		return validateId(taskDTO.getId())
                && validatePrice(taskDTO.getPrice())
                && validateTitle(taskDTO.getTitle())
				&& validateDescription(taskDTO.getDescription())  ;
	}

    public boolean validateDeleteRigth(TaskDTO taskDTO) {
        return validateId(taskDTO.getId());
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
