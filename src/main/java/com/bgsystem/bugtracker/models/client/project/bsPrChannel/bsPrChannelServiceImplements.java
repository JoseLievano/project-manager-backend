package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrChannelServiceImplements extends DefaultServiceImplements <bsPrChannelDTO, bsPrChannelMiniDTO, bsPrChannelForm, bsPrChannelEntity, Long> {

    @Autowired
    private bsProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private bsPrChannelRepository channelRepository;

    public bsPrChannelServiceImplements(bsPrChannelRepository respository, bsPrChannelMapper mapper){
        super(respository, mapper);
    }

    @Override
    public bsPrChannelMiniDTO insert(bsPrChannelForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getAuthor() == null || form.getProject() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Generate the entity from the form
        bsPrChannelEntity toInsert = mapper.toEntity(form);

        //Check if the project exist, if exist insert the project in the channel
        if (projectRepository.existsById(form.getProject())){
            toInsert.setProject(projectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundExeption("Project not found")));
        }

        //Check if the author exist, if exist insert the author in the channel
        if (userRepository.existsById(form.getAuthor())){
            toInsert.setAuthor(userRepository.findById(form.getAuthor()).orElseThrow(() -> new ElementNotFoundExeption("Author not found")));
        }

        //Check if there are members for this channel, if there are insert them in the channel
        if (form.getMembers() != null && form.getMembers().size() > 0){
            for (Long memberID : form.getMembers()){
                toInsert.getMembers().add(userRepository.findById(memberID).orElseThrow(() -> new ElementNotFoundExeption("Member not found")));
            }
        }

        //Check if the channel already exist, if exist throw an exception
        if (channelRepository.existsByNameAndProject(form.getName(), toInsert.getProject())){
            throw new ElementAlreadyExist("Channel already exist");
        }

        //Save the channel
        repository.save(toInsert);

        //Save the changes in the project
        toInsert.getProject().getChannels().add(toInsert);
        projectRepository.save(toInsert.getProject());

        //Save the author
        toInsert.getAuthor().getChannelsAuthor().add(toInsert);
        userRepository.save(toInsert.getAuthor());

        //Save the members
        toInsert.getMembers().forEach(member -> {
            member.getChannels().add(toInsert);
            userRepository.save(member);
        });

        return mapper.toSmallDTO(toInsert);
    }
}
