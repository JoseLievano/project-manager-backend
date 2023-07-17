package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlanServiceImplements extends DefaultServiceImplements<PlanDTO, PlanMiniDTO, PlanListDTO, PlanForm, PlanEntity, Long> {

    private final PlanRepository planRepository;

    private final MainHQRepository mainHQRepository;

    private final PlanPredicate planPredicate;

    @Autowired
    public PlanServiceImplements(
                                PlanRepository repository,
                                PlanMapper mapper,
                                MainHQRepository mainHQRepository,
                                PlanPredicate planPredicate
    ) {
        super(repository, mapper, planPredicate);
        this.planRepository = repository;
        this.mainHQRepository = mainHQRepository;
        this.planPredicate = planPredicate;
    }

    @Override
    public PlanMiniDTO insert(PlanForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getPrice() == null) {
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new plan");
        }

        Set<PlanEntity> planExistenceCheck = planRepository.findByName(form.getName());

        if (form.getId() != null) {
            planExistenceCheck.add(repository.findById(form.getId()).orElseThrow(null));
        }

        if (planExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist("The plan already exist in our DB");
        }

        PlanEntity toInsert = mapper.toEntity(form);

        //Insert the MainHQ in the plan
        MainHQEntity mainHQEntity = mainHQRepository.findAll().get(0);

        if (mainHQEntity == null) {
            throw new ElementNotFoundException("The MainHQ is not found in our DB");
        }else {
            toInsert.setMainHQEntity(mainHQEntity);
        }

        //Save the plan
        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);
    }
}
