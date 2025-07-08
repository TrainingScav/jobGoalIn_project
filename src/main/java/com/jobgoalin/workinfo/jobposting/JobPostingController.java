package com.jobgoalin.workinfo.jobposting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class JobPostingController {

    private final JobPostingService jobPostingService;

    @GetMapping("/jobpostingboard")
    public String boardList(Model model) {
        model.addAttribute("jobPostingBoardList", jobPostingService .findAll());
        return "board/jobPosting";
    }

    @GetMapping("/jobpostingboard/detail/{recruitId}")
    public String detail(@PathVariable Long recruitId, Model model) {
        model.addAttribute("jobPostingBoard", jobPostingService.findById(recruitId));
        return "board/detail";
    }

    @GetMapping("/jobpostingboard/write")
    public String showWriteForm() {
        return "board/save-form";
    }

    // 등록 처리
    @PostMapping("/posting/write")
    public String submitJobPosting(JobPostingBoard jobPostingBoard) {
        jobPostingService.save(jobPostingBoard);
        return "redirect:/jobpostingboard";
    }

    @GetMapping("/jobpostingboard/{recruitId}/update-form")
    public String updateForm(@PathVariable Long recruitId, Model model) {
        model.addAttribute("jobPostingBoard", jobPostingService.findById(recruitId));
        return "board/update-form";
    }

    @PostMapping("/jobpostingboard/{recruitId}/update")
    public String update(@PathVariable Long recruitId, JobPostingBoard updated) {
        jobPostingService.update(recruitId, updated);
        return "redirect:/jobpostingboard/detail/" + recruitId;
    }

    @PostMapping("/jobpostingboard/{recruitId}/delete")
    public String delete(@PathVariable Long recruitId) {
        jobPostingService.delete(recruitId);
        return "redirect:/jobpostingboard";
    }

}










