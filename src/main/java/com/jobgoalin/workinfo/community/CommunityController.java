package com.jobgoalin.workinfo.community;

import com.jobgoalin.workinfo.user.LoginUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 전체 게시글 목록
    @GetMapping("/community/list")
    public String showList(Model model) {
        List<Community> posts = communityService.findAllPosts();
        model.addAttribute("posts", posts);
        return "community/list";
    }

    @GetMapping("/community/detail/{postId}")
    public String showDetail(@PathVariable(name = "postId") Long postId, Model model) {
        model.addAttribute("post", communityService.findById(postId));
        return "community/detail";
    }

    @GetMapping("/community/write")
    public String showWriteForm() {
        return "community/save-form";
    }

    // 글 작성 처리
    @PostMapping("/communityposting/write")
    public String submitPost(@ModelAttribute("save-form") CommunityRequest.SaveDTO dto,
                             HttpSession sessionUser) {
        LoginUser loginUser = (LoginUser) sessionUser.getAttribute("sessionUser");

        if (loginUser != null) {
            dto.setInstId(loginUser.getLoginId());
        } else {
            dto.setInstId("anonymous");
        }

        communityService.savePost(dto);
        return "redirect:/community/list";
    }

    // 글 수정 폼
    @GetMapping("/{postId}/update-form")
    public String showUpdateForm(@PathVariable(name="postId") Long postId, Model model) {
        Community post = communityService.findById(postId);
        CommunityRequest.UpdateDTO updateDTO = new CommunityRequest.UpdateDTO();
        updateDTO.setTitle(post.getTitle());
        updateDTO.setContent(post.getContent());
        model.addAttribute("updateDTO", updateDTO);
        model.addAttribute("postId", postId);
        return "community/update-form";
    }

    // 글 수정 처리
    @PostMapping("/{postId}/update")
    public String updatePost(@PathVariable Long postId,
                             @ModelAttribute("updateDTO") CommunityRequest.UpdateDTO dto,
                             HttpSession sessionUser) {

        LoginUser loginUser = (LoginUser)sessionUser.getAttribute("sessionUser");
        if(loginUser != null){
            communityService.updatePost(postId, dto);
            return "redirect:/community/detail/" + postId;
        }else {
            // 1. 사용자에게 값 받고
            // 2. 글 쓸때 설정한 password와 값 비교 후 일치시 삭제
        }
        return "redirect:/community/detail/" + postId;
    }

    // 글 삭제
    @PostMapping("/community/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return "redirect:/community/list";
    }
}