package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainHQServiceImplements extends DefaultServiceImplements<MainHQDTO, MainHQMiniDTO, MainHQListDTO, MainHQForm, MainHQEntity, Long> {

    private final MainHQRepository mainHQRepository;

    private final MainHQPredicate mainHQPredicate;

    @Autowired
    public MainHQServiceImplements(
                                    MainHQRepository repository,
                                    MainHQMapper mapper,
                                    MainHQPredicate mainHQPredicate
    ) {
        super(repository, mapper, mainHQPredicate);
        this.mainHQRepository = repository;
        this.mainHQPredicate = mainHQPredicate;
    }

    @Override
    public MainHQMiniDTO insert(MainHQForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null) {
            throw new InvalidInsertDeails("Form is invalid");
        }

        //Check if there is already a mainHQ in the DB
        List<MainHQEntity> mainHQExistenceCheck = repository.findAll();

        if (mainHQExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist("There is already a mainHQ");
        }

        MainHQEntity entity = mapper.toEntity(form);

        entity = repository.save(entity);

        return mapper.toSmallDTO(entity);

    }
}
