package com.bgsystem.bugtracker.shared.tools;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentRepository;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionRepository;
import com.bgsystem.bugtracker.shared.models.user.User;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class MentionGenerator {

    public static Set<bsPrMentionEntity> generateMentions(bsPrCommentEntity comment, UserRepository userRepository, bsPrMentionRepository mentionRepository){

        List<String> users = Arrays.asList(comment.getCommentContent().split(" "))
                .stream()
                .filter(word -> word.startsWith("@"))
                .collect(Collectors.toList());

        users =  users.stream().map(user -> user.substring(1)).collect(Collectors.toList());

        Set<bsPrMentionEntity> mentions = new HashSet<>();

        for (String user : users){
            Boolean userExist = userRepository.existsByUsername(user);

            if (userExist){

                bsPrMentionEntity mention = new bsPrMentionEntity();

                mention.setAuthor(comment.getAuthor());

                mention.setMentionedUser(userRepository.findByUsername(user).iterator().next());

                mention.setMentionDate(comment.getCommentDate());

                mention.setComment(comment);

                mentionRepository.save(mention);

                mentions.add(mention);
            }
        }

        return mentions;
    }

}
