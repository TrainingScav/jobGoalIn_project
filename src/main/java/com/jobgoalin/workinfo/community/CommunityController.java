package com.jobgoalin.workinfo.community;

import com.jobgoalin.workinfo._core.common.PageLink;
import com.jobgoalin.workinfo._core.errors.exception.Exception403;
import com.jobgoalin.workinfo.user.LoginUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 전체 게시글 목록
    @GetMapping("/community/list")
    public String communityPage(Model model,
                                         @RequestParam(defaultValue = "0")int page,
                                         @RequestParam(defaultValue = "10")int size){
        Pageable pageable = PageRequest.of(page ,size, Sort.by("instDate").descending());
        Page<Community> communityPage = communityService.findAllPosts(pageable);
        // 페이지 네비게이션 용 데이터 준비
        List<PageLink> pageLinks = new ArrayList<>();
        for(int i = 0; i < communityPage.getTotalPages(); i++) {
            pageLinks.add(new PageLink(i, i + 1, i == communityPage.getNumber()));
        }

        Integer previousPageNumber = communityPage.hasPrevious() ? communityPage.getNumber() : null;
        Integer nextPageNumber = communityPage.hasNext() ? communityPage.getNumber() + 2 : null;

        // 뷰 화면에 데이터 전달
        model.addAttribute("communityPage", communityPage);
        // 페이지 네비게이션에 사용할 번호 링크 리스트
        model.addAttribute("pageLinks", pageLinks);
        // 이전 페이지 번호 (없으면 null)
        model.addAttribute("previousPageNumber", previousPageNumber);
        // 다음 페이지 번호 (없으면 null)
        model.addAttribute("nextPageNumber", nextPageNumber);


        return "/community/list";
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
    @PostMapping("/community/write")
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
    @GetMapping("/community/{postId}/update")
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
    @PostMapping("community/{postId}/update")
    public String updatePost(@PathVariable Long postId,
                             @ModelAttribute("updateDTO") CommunityRequest.UpdateDTO dto,
                             HttpSession sessionUser) {
        Community post = communityService.findById(postId);
        LoginUser loginUser = (LoginUser)sessionUser.getAttribute("sessionUser");
        if(loginUser.getLoginId().equals(post.getInstId())){
            communityService.updatePost(postId, dto);
            return "redirect:/community/detail/" + postId;
        }else {
            throw new Exception403("권한없음");
        }

    }

    // 글 삭제
    @PostMapping("/community/{postId}/delete")
    public String deletePost(@PathVariable Long postId,HttpSession sessionUser) {
        Community post = communityService.findById(postId);
        LoginUser loginUser = (LoginUser) sessionUser.getAttribute("sessionUser");
        if (loginUser.getLoginId().equals(post.getInstId())) {
            communityService.deletePost(postId);
            return "redirect:/community/list";
        } else {
            throw new Exception403("권한없음");
        }
    }
}