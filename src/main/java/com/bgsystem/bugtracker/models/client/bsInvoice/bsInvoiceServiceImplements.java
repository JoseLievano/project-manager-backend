package com.bgsystem.bugtracker.models.client.bsInvoice;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsInvoiceServiceImplements extends DefaultServiceImplements<bsInvoiceDTO, bsInvoiceMiniDTO, bsInvoiceForm, bsInvoiceEntity, Long> {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private bsProjectRepository projectRepository;

    @Autowired
    private bsClientRepository clientRepository;

    @Autowired
    private bsPrTaskRepository taskRepository;

    public bsInvoiceServiceImplements(bsInvoiceRepository repository, bsInvoiceMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public bsInvoiceMiniDTO insert(bsInvoiceForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getAmount() == null || form.getClient() == null || form.getBusiness() == null ){
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Chech if the invoice has a project or a task associated with it
        if (form.getProject() == null && form.getTask() == null){
            throw new InvalidInsertDeails("Invalid insert details, invoice must have a project or a task associated with it");
        }

        bsInvoiceEntity toInsert = mapper.toEntity(form);

        //Get the business from the form
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));
        business.getBsInvoiceEntities().add(toInsert);

        //Get the client from the form
        bsClientEntity client = clientRepository.findById(form.getClient()).orElseThrow(() -> new ElementNotFoundExeption("Client not found"));
        client.getInvoices().add(toInsert);

        toInsert.setBusiness(business);
        toInsert.setClient(client);

        //Create an empty task
        bsPrTaskEntity task = new bsPrTaskEntity();

        //Create an empty project
        bsProjectEntity project = new bsProjectEntity();

        //Check if there is a task or a project associated with the invoice
        if (form.getTask() != null){

            task = taskRepository.findById(form.getTask()).orElseThrow(() -> new ElementNotFoundExeption("Task not found"));
            task.setInvoice(toInsert);
            toInsert.setTask(task);

            project = task.getProject();
            project.getTasks().add(task);
            toInsert.setProject(project);

        }else{

            task = null;
            project = projectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundExeption("Project not found"));
            project.getInvoices().add(toInsert);

            toInsert.setProject(project);

        }

        //Set the created date to today
        Date today = new Date();
        toInsert.setDateGenerated(today);

        if (form.getIsPaid() == null) {
            toInsert.setIsPaid(false);
        }

        toInsert.setIsOverDue(false);

        toInsert.setNumber("");

        //Check if there is a limitDate and if it is in the future
        if(form.getLimitDate() != null){

            if(form.getLimitDate().before(new Date())){
                throw new InvalidInsertDeails("Invalid insert details, limit date must be in the future");
            }else {
                toInsert.setLimitDate(form.getLimitDate());
            }

        }

        repository.save(toInsert);

        businessRepository.save(business);
        clientRepository.save(client);
        projectRepository.save(project);

        if (task != null){
            taskRepository.save(task);
        }

        return mapper.toSmallDTO(toInsert);

    }
}
