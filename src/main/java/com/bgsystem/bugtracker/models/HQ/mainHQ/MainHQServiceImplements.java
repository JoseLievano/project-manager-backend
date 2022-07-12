package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MainHQServiceImplements extends DefaultServiceImplements<MainHQDTO, MainHQMiniDTO, MainHQForm, MainHQEntity, Long> {

    @Autowired
    private MainHQRepository mainHQRepository;

    public MainHQServiceImplements(MainHQRepository repository, MainHQMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public MainHQMiniDTO insert(MainHQForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null) {
            throw new InvalidInsertDeails("Form is invalid");
        }

        //Check if MainHQ already exists in the DB
        Set< MainHQEntity> mainHQEntity = mainHQRepository.findByName(form.getName());

        if (mainHQEntity.size() > 0) {
            throw new ElementAlreadyExist("MainHQ already exists");
        }

        MainHQEntity entity = mapper.toEntity(form);
        entity = repository.save(entity);
        return mapper.toSmallDTO(entity);

    }
}
