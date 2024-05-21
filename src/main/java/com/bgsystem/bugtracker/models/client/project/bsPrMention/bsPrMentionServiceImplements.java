package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentRepository;
import com.bgsystem.bugtracker.shared.models.user.User;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrMentionServiceImplements extends DefaultServiceImplements <bsPrMentionDTO, bsPrMentionMiniDTO, bsPrMentionListDTO, bsPrMentionForm, bsPrMentionEntity, Long> {

    private final bsPrMentionRepository mentionRepository;

    private final UserRepository userRepository;

    private final bsPrCommentRepository commentRepository;

    private final bsPrMentionPredicate bsPrMentionPredicate;

    @Autowired
    public bsPrMentionServiceImplements (bsPrMentionRepository repository,
                                         bsPrMentionMapper mapper,
                                         bsPrMentionRepository mentionRepository,
                                         UserRepository userRepository,
                                         bsPrCommentRepository commentRepository,
                                         bsPrMentionPredicate bsPrMentionPredicate) {
        super(repository, mapper, bsPrMentionPredicate);
        this.mentionRepository = mentionRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.bsPrMentionPredicate = bsPrMentionPredicate;
    }

    @Override
    public bsPrMentionMiniDTO insert(bsPrMentionForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getAuthor() == null || form.getMentionedUser() == null || form.getComment() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Get the entity from the form
        bsPrMentionEntity toInsert = mapper.toEntity(form);

        //Check if the author and the mentioned user are the same
        if (form.getAuthor().equals(form.getMentionedUser())){
            throw new InvalidInsertDeails("The author and the mentioned user are the same");
        }

        //Check if the author exists and insert the author in the mention
        User author = userRepository.findById(form.getAuthor()).orElseThrow(() -> new ElementNotFoundException("The author does not exist"));
        toInsert.setAuthor(author);

        User mentionedUser = userRepository.findById(form.getMentionedUser()).orElseThrow(() -> new ElementNotFoundException("The mentioned user does not exist"));
        toInsert.setMentionedUser(mentionedUser);

        //Check if the comment exists and insert the comment in the mention
        bsPrCommentEntity comment = commentRepository.findById(form.getComment()).orElseThrow(() -> new ElementNotFoundException("The comment does not exist"));
        toInsert.setComment(comment);

        //Check if the mention already exists
        if (mentionRepository.existsByCommentAndAuthorAndMentionedUser(comment, author, mentionedUser)){

            //If the mention already exist, return the mention

            toInsert = mentionRepository.findByCommentAndAuthorAndMentionedUser(comment, author, mentionedUser).iterator().next();

            return mapper.toSmallDTO(toInsert);

        }

        //If the mention does not exist, insert the mention and return the mention
        repository.save(toInsert);

        commentRepository.save(comment);

        return mapper.toSmallDTO(toInsert);
    }
}
