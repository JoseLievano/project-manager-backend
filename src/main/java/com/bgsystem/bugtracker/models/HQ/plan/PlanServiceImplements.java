package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlanServiceImplements extends DefaultServiceImplements<PlanDTO, PlanMiniDTO, PlanForm, PlanEntity, Long> {

    @Autowired
    private PlanRepository planRepository;

    public PlanServiceImplements(PlanRepository repository, PlanMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public PlanMiniDTO insert(PlanForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

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

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);
    }
}
