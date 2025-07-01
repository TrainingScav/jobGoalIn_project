package com.jobgoalin.workinfo.jobposting;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // Mustache 템플릿을 반환하려면 @RestController 대신 @Controller 사용
@RequiredArgsConstructor

public class JobPostingController {

    private final JobPostingRepository jobPostingRepository;

    // 전체 채용공고 목록 조회
    @GetMapping("/posting")
    public String boardList(Model model) {
        List<JobPostingBoard> jobPostingBoardList = jobPostingRepository.findAll();
        model.addAttribute("jobPostingBoardList", jobPostingBoardList );
        return "jobposting/jobPosting"; // resources/templates/jobposting-list.mustache
    }

    // 상세 보기 화면
//    @GetMapping("/posting/deatil")
//    public String detail(Model model){
//
//    }


}
