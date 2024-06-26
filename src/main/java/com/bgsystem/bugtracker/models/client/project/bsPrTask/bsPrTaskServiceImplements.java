package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityEntity;
import com.bgsystem.bugtracker.models.client.bsPriority.bsPriorityRepository;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusEntity;
import com.bgsystem.bugtracker.models.client.bsStatus.bsStatusRepository;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryRepository;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeEntity;
import com.bgsystem.bugtracker.models.client.bsType.bsTypeRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrTaskServiceImplements extends DefaultServiceImplements <bsPrTaskDTO, bsPrTaskMiniDTO, bsPrTaskListDTO, bsPrTaskForm, bsPrTaskEntity, Long> {

    private final BusinessRepository businessRepository;

    private final bsPrTaskRepository bsPrTaskRepository;

    private final bsProjectRepository projectRepository;

    private final bsTypeRepository typeRepository;

    private final bsStatusRepository statusRepository;

    private final bsPriorityRepository priorityRepository;

    private final bsTaskCategoryRepository categoryRepository;

    private final bsPrTaskPredicate bsPrTaskPredicate;

    @Autowired
    public bsPrTaskServiceImplements(bsPrTaskRepository repository,
                                     bsPrTaskMapper mapper,
                                     BusinessRepository businessRepository,
                                     bsPrTaskRepository bsPrTaskRepository,
                                     bsProjectRepository projectRepository,
                                     bsTypeRepository typeRepository,
                                     bsStatusRepository statusRepository,
                                     bsPriorityRepository priorityRepository,
                                     bsTaskCategoryRepository categoryRepository,
                                     bsPrTaskPredicate bsPrTaskPredicate) {
        super (repository, mapper, bsPrTaskPredicate);
        this.businessRepository = businessRepository;
        this.bsPrTaskRepository = bsPrTaskRepository;
        this.projectRepository = projectRepository;
        this.typeRepository = typeRepository;
        this.statusRepository = statusRepository;
        this.priorityRepository = priorityRepository;
        this.categoryRepository = categoryRepository;
        this.bsPrTaskPredicate = bsPrTaskPredicate;
    }

    @Override
    public bsPrTaskMiniDTO insert(bsPrTaskForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getCategory() == null || form.getProject() == null || form.getType() == null || form.getPriority() == null || form.getStatus() == null || form.getName() == null ) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Create the task entity
        bsPrTaskEntity toInsert = mapper.toEntity(form);

        //Check if the associated business exists
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));
        business.getBsPrTasks().add(toInsert);

        //Check if the associated project exists
        bsProjectEntity project = projectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundException("Project not found"));
        project.getTasks().add(toInsert);

        //Check if the associated category exists
        bsTaskCategoryEntity category = categoryRepository.findById(form.getCategory()).orElseThrow(() -> new ElementNotFoundException("Category not found"));
        category.getTasks().add(toInsert);

        //Check if the associated type exists
        bsTypeEntity type = typeRepository.findById(form.getType()).orElseThrow(() -> new ElementNotFoundException("Type not found"));
        type.getTasks().add(toInsert);

        //Check if the associated priority exists
        bsPriorityEntity priority = priorityRepository.findById(form.getPriority()).orElseThrow(() -> new ElementNotFoundException("Priority not found"));
        priority.getTasks().add(toInsert);

        //Check if the associated status exists
        bsStatusEntity status = statusRepository.findById(form.getStatus()).orElseThrow(() -> new ElementNotFoundException("Status not found"));
        status.getTasks().add(toInsert);

        //Check if a task with the same name in the same business, project and category already exists
        if(bsPrTaskRepository.findByNameAndBusinessAndCategoryAndProject(form.getName(), business, category, project).size() > 0){
            throw new ElementAlreadyExist("A task with the same name in the same business, project and category already exists");
        }

        //Start setting the external entities to the task
        toInsert.setBusiness(business);
        toInsert.setCategory(category);
        toInsert.setProject(project);
        toInsert.setStatus(status);
        toInsert.setPriority(priority);
        toInsert.setType(type);

        //Set the task created date
        Date today = new Date();
        toInsert.setCreated(today);

        //Check if isExternal field is null, if it is, then set it to true
        if (toInsert.getIsInternal() == null){
            toInsert.setIsInternal(true);
        }

        //Check if isDone field is null, if it is, then set it to false
        if (toInsert.getIsDone() == null){
            toInsert.setIsDone(false);
        }

        //Check if isOverdue field is null, if it is, then set it to false
        if (toInsert.getIsOverDue() == null){
            toInsert.setIsOverDue(false);
        }

        repository.save(toInsert);

        businessRepository.save(business);
        categoryRepository.save(category);
        projectRepository.save(project);
        statusRepository.save(status);
        priorityRepository.save(priority);
        typeRepository.save(type);

        return mapper.toSmallDTO(toInsert);

    }
}
