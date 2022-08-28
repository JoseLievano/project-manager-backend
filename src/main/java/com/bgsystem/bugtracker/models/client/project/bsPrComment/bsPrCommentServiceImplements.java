package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionRepository;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.models.user.User;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import com.bgsystem.bugtracker.shared.tools.MentionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class bsPrCommentServiceImplements extends DefaultServiceImplements <bsPrCommentDTO, bsPrCommentMiniDTO, bsPrCommentForm, bsPrCommentEntity, Long> {

    @Autowired
    private bsPrChannelRepository channelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private bsProjectRepository projectRepository;

    @Autowired
    private bsPrMentionRepository mentionRepository;

    @Autowired
    private bsPrCommentRepository commentRepository;

    public bsPrCommentServiceImplements(bsPrCommentRepository repository, bsPrCommentMapper mapper){
        super(repository, mapper);
    }

    @Override
    public bsPrCommentMiniDTO insert(bsPrCommentForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getAuthor() == null || form.getCommentContent() == null || form.getChannel() == null){
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Generate the entity from the form
        bsPrCommentEntity toInsert = mapper.toEntity(form);

        //Check if the channel exist, if exist insert the channel in the comment
        bsPrChannelEntity channel = channelRepository.findById(form.getChannel()).orElseThrow(() -> new ElementNotFoundExeption("Channel not found"));
        channel.getComments().add(toInsert);
        toInsert.setChannel(channel);

        //Check if project exist, if exist insert the project in the comment
        bsProjectEntity project = channel.getProject();
        toInsert.setProject(project);

        //Check if the author exist, if exist insert the author in the comment
        User author = userRepository.findById(form.getAuthor()).orElseThrow(() -> new ElementNotFoundExeption("Author not found"));
        author.getComments().add(toInsert);
        toInsert.setAuthor(author);

        //Set creation date to today
        toInsert.setCommentDate(new Date());

        //Save the comment
        repository.save(toInsert);

        //Use the tool to check if we need to generate mentions

        Set<bsPrMentionEntity> mentions = new HashSet<>();
        mentions.addAll(MentionGenerator.generateMentions(toInsert, userRepository, mentionRepository));




        if (!mentions.isEmpty()){
            for (bsPrMentionEntity mention : mentions){
                System.out.println("Date: " + mention.getMentionDate());
                System.out.println("Author: " + mention.getAuthor().getUsername());
                System.out.println("Mentioned: " + mention.getMentionedUser().getUsername());
                mentionRepository.save(mention);
            }
        }


        userRepository.save(author);
        channelRepository.save(channel);
        projectRepository.save(project);

        return mapper.toSmallDTO(toInsert);
    }
}
