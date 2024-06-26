package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.models.user.User;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsPrChannelServiceImplements extends DefaultServiceImplements <bsPrChannelDTO, bsPrChannelMiniDTO, bsPrChannelListDTO, bsPrChannelForm, bsPrChannelEntity, Long> {

    private final bsProjectRepository projectRepository;

    private final UserRepository userRepository;

    private final bsPrChannelRepository channelRepository;

    private final bsPrChannelPredicate bsPrChannelPredicate;
    @Autowired
    public bsPrChannelServiceImplements(
                                        bsPrChannelRepository respository,
                                        bsPrChannelMapper mapper,
                                        bsProjectRepository projectRepository,
                                        UserRepository userRepository,
                                        bsPrChannelRepository channelRepository,
                                        bsPrChannelPredicate bsPrChannelPredicate
    ) {
        super(respository, mapper, bsPrChannelPredicate);
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
        this.bsPrChannelPredicate = bsPrChannelPredicate;
    }

    @Override
    public bsPrChannelMiniDTO insert(bsPrChannelForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getAuthor() == null || form.getProject() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Generate the entity from the form
        bsPrChannelEntity toInsert = mapper.toEntity(form);

        //Check if the project exist, if exist insert the project in the channel
        bsProjectEntity project = projectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundException("Project not found"));
        project.getChannels().add(toInsert);

        //Check if the author exist, if exist insert the author in the channel
        User author = userRepository.findById(form.getAuthor()).orElseThrow(() -> new ElementNotFoundException("Author not found"));
        author.getChannelsAuthor().add(toInsert);

        //Check if there are members for this channel, if there are insert them in the channel

        boolean hasMembers = form.getMembers() != null;

        if (hasMembers){
            for (Long memberID : form.getMembers()){
                User member = userRepository.findById(memberID).orElseThrow(() -> new ElementNotFoundException("Member not found"));
                member.getChannels().add(toInsert);
                toInsert.getMembers().add(member);
            }
        }

        //Check if the channel already exist, if exist throw an exception
        if (channelRepository.existsByNameAndProject(form.getName(), toInsert.getProject())){
            throw new ElementAlreadyExist("Channel already exist");
        }

        if (form.getIsPublic() == null){
            toInsert.setIsPublic(true);
        }

        if (form.getCreationDate() == null){
            toInsert.setCreationDate(new Date());
        }

        toInsert.setProject(project);
        toInsert.setAuthor(author);

        //Save the channel
        repository.save(toInsert);

        //Save the changes in the project
        projectRepository.save(toInsert.getProject());

        //Save the author
        toInsert.getAuthor().getChannelsAuthor().add(toInsert);
        userRepository.save(toInsert.getAuthor());

        //Save the members
        if (hasMembers){
            userRepository.saveAll(toInsert.getMembers());
        }

        return mapper.toSmallDTO(toInsert);
    }

    @Override
    protected bsPrChannelEntity updateListFields(bsPrChannelEntity bsPrChannelEntity) {

        bsPrChannelEntity.setCommentsCount(bsPrChannelEntity.getComments() == null ? 0 : (long) bsPrChannelEntity.getComments().size());
        bsPrChannelEntity.setMembersCount(bsPrChannelEntity.getMembers() == null ? 0 : (long) bsPrChannelEntity.getMembers().size());

        return bsPrChannelEntity;

    }
}
