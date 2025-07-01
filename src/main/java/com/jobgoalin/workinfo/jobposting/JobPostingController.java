package com.jobgoalin.workinfo.jobposting;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller // Mustache 템플릿을 반환하려면 @RestController 대신 @Controller 사용
@RequiredArgsConstructor

public class JobPostingController {

    private final JobPostingRepository jobPostingRepository;
    private static final Logger log = LoggerFactory.getLogger(JobPostingController.class);

    // 전체 채용공고 목록 조회
    @GetMapping("/board")
    public String boardList(Model model) {
        List<JobPostingBoard> jobPostingBoardList = jobPostingRepository.findAll();

        log.info("jobPostingBoardList 데이터 확인 : {}", jobPostingBoardList);
        log.info("jobPostingBoardList 사이즈 확인 : {}", jobPostingBoardList.size());

        model.addAttribute("jobPostingBoardList", jobPostingBoardList );
        return "board/jobPosting"; // resources/templates/jobposting-list.mustache
    }

    // 상세 보기 화면
    @GetMapping("/board/detail/{recruitId}")
    public String detail(@PathVariable("recruitId") Long recruitId, Model model) {
        JobPostingBoard jobPostingBoard = jobPostingRepository.findByRecruitId(recruitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("jobPostingBoard", jobPostingBoard);
        return "board/detail";  // templates/board/detail.mustache
    }

    // 등록 폼 보여주기
    @GetMapping("/board/write")
    public String showWriteForm() {
        return "board/save-form"; // templates/board/write.mustache
    }

    // 등록 처리
    @PostMapping("/posting/write")
    public String submitJobPosting(JobPostingBoard jobPostingBoard) {
        jobPostingRepository.save(jobPostingBoard);
        return "redirect:/board";  // 등록 후 목록 페이지로 이동
    }

    // 게시물 삭제처리
    @PostMapping("/board/{recruitId}/delete")
    public String delete(@PathVariable("recruitId")Long recruitId, HttpSession session){
        Optional<JobPostingBoard> jobPostingBoard = jobPostingRepository.findByRecruitId(recruitId);
        jobPostingRepository.deleteById(recruitId);
        return "redirect:/board";
    }





    }







