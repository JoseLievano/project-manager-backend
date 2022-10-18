package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("bs_pr_comment")
public class bsPrCommentController extends DefaultController <bsPrCommentDTO, bsPrCommentMiniDTO, bsPrCommentForm, Long>{

    bsPrCommentServiceImplements extraService;
    public bsPrCommentController (bsPrCommentServiceImplements service, bsPrCommentServiceImplements extraService) {
        super(service);
        this.extraService = extraService;
    }

    @GetMapping("/pagina/{channel}/{page}/{size}")
    public List<bsPrCommentDTO> pagina(@PathVariable Long channel, @PathVariable Integer page, @PathVariable Integer size) throws ElementNotFoundExeption {

        return extraService.getAllByChannel(channel, page, size);
    }
}
