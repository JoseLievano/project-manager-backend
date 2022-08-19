package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.stereotype.Service;

@Service
public class bsPrChannelServiceImplements extends DefaultServiceImplements <bsPrChannelDTO, bsPrChannelMiniDTO, bsPrChannelForm, bsPrChannelEntity, Long> {

    public bsPrChannelServiceImplements(bsPrChannelRepository respository, bsPrChannelMapper mapper){
        super(respository, mapper);
    }

    @Override
    public bsPrChannelMiniDTO insert(bsPrChannelForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {
        return super.insert(bsPrChannelForm);
    }
}
