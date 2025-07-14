package com.jobgoalin.workinfo.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 1) 전체 게시글 목록
    @GetMapping("/communityboard")
    public String boardList(Model model) {
        List<Community> communityBoardList = communityService.findAll();
        model.addAttribute("communityBoardList", communityBoardList);
        return "community/board";
    }

    // 2) 게시글 상세보기
    @GetMapping("/communityboard/detail/{postId}")
    public String detail(@PathVariable Long postId, Model model) {
        Community communityBoard = communityService.findById(postId);
        model.addAttribute("communityBoard", communityBoard);
        return "community/detail";
    }

    // 3) 작성폼
    @GetMapping("/communityboard/write")
    public String showWriteForm() {
        return "community/save-form";
    }

    // 4) 게시글 저장
    @PostMapping("/communityboard/write")
    public String submitCommunity(Community community) {
        communityService.save(community);
        return "redirect:/communityboard";
    }

    // 5) 수정폼
    @GetMapping("/communityboard/{postId}/update-form")
    public String updateForm(@PathVariable Long postId, Model model) {
        Community communityBoard = communityService.findById(postId);
        model.addAttribute("communityBoard", communityBoard);
        return "community/update-form";
    }

    // 6) 게시글 수정
    @PostMapping("/communityboard/{postId}/update")
    public String update(@PathVariable Long postId, Community updated) {
        communityService.update(postId, updated);
        return "redirect:/communityboard/detail/" + postId;
    }

    // 7) 게시글 삭제
    @PostMapping("/communityboard/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        communityService.delete(postId);
        return "redirect:/communityboard";
    }
}