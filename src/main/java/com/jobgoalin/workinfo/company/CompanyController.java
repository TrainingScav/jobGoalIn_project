package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo._core.errors.exception.Exception400;
import com.jobgoalin.workinfo._core.errors.exception.Exception500;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompanyController {


    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;
    private final UserService userService;

    @GetMapping("/company/list")
    public String companyInfoList(Model model) {

        log.info(">> 기업정보 목록 조회 시작 << ");

        List<CompanyInfo> companyInfoList = companyService.findAllCompanyInfo();
        model.addAttribute("companyInfoList", companyInfoList);

        return "company/company_list";
    }

    @GetMapping("/company/{id}")
    public String companyInfoDetail(@PathVariable(name = "id") Long id, Model model) {

        log.info(">> 기업 상세정보 화면이동 및 조회 시작 << ");

        CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);
        model.addAttribute("companyInfo", companyInfoDetail);

        return "company/company_detail";
    }

    @GetMapping("/company/form")
    public String companyInfoForm() {

        log.info(">> 기업정보 작성화면 이동 << ");

        return "company/company_form";
    }

    @PostMapping("/company/form")
    public String companyInfoInsert(CompanyRequest.SaveDTO saveDTO) {

        log.info(">> 기업정보 등록 시작 << ");

        CompanyInfo companyInfo = companyService.companyInfoInsert(saveDTO.toEntity());

        if (companyInfo == null) {
            throw new Exception500("등록 처리 중 에러가 발생했습니다.");
        }

        return "redirect:/company/list";
    }

    @GetMapping("/company/{id}/update")
    public String companyInfoUpdateForm(@PathVariable(name = "id") Long id, Model model) {

        log.info(">> 기업정보 수정 화면이동 및 조회 시작 << ");

        CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);
        model.addAttribute("companyInfo", companyInfoDetail);

        return "company/company_update";
    }

    @PostMapping("/company/{id}/update")
    public String companyInfoUpdate(@PathVariable(name = "id") Long id, CompanyRequest.SaveDTO saveDTO) {

        log.info(">> 기업정보 수정 시작 << ");

        CompanyInfo companyInfo = companyService.companyInfoUpdate(id, saveDTO.toEntity());
        if (companyInfo == null) {
            throw new Exception500("수정 처리 중 에러가 발생했습니다.");
        }

        return "redirect:/company/"+id;
    }

    @PostMapping("/company/{id}/delete")
    public String companyInfoDelete(@PathVariable(name = "id") Long id) {

        log.info(">> 기업정보 삭제 시작 << ");

        companyService.companyInfoDelete(id);
        return "redirect:/company/list";
    }

    @GetMapping("/company/{id}/reviews")
    public String companyReview(@PathVariable(name = "id") Long companyId, Model model, HttpSession session)  {

        // 임시 테스트를 위한 세션유저 세팅
        User user = (User)session.getAttribute("sessionUser");

        if (user == null) {
            user = userService.findById(1L);
            session.setAttribute("sessionUser", user);
        }

        List<CompanyReview> companyReviews = companyService.findCompanyReviewByCompanyId(companyId);

        // 리뷰 소유권 설정 (삭제 버튼 표시용)
        if (user != null) {
            User searchUserId = user;
            companyReviews.forEach(companyReview -> {
                boolean isReviewOwner = companyReview.isOwner(searchUserId.getUserId());
                companyReview.setIsMyReview(isReviewOwner);
                log.info("isMyReview 확인 : {}", companyReview.getIsMyReview());
            });
        }

        log.info("리뷰 개수 확인 : {}", companyReviews.size());

        model.addAttribute("companyId", companyId);
        model.addAttribute("reviews", companyReviews);

        return "company/company_reviews";
    }

    @GetMapping("/company/{id}/reviews/form")
    public String companyReviewForm(@PathVariable(name = "id") Long companyId, Model model) {

        model.addAttribute("companyId", companyId);

        return "company/company_review_form";
    }

    @PostMapping("/company/{id}/reviews/form")
    public String companyReviewFormInsert(@PathVariable(name = "id") Long companyId, Model model, CompanyRequest.SaveReviewDTO saveReviewDTO, HttpSession session) {

        // String -> boolean 변환 처리를 위한 설정
        saveReviewDTO.setCurrentEmployee(saveReviewDTO.getIsCurrentEmployeeYn().equals("true"));
        saveReviewDTO.setRecommended(saveReviewDTO.getIsRecommendedYn().equals("true"));

        CompanyInfo companyInfo = companyService.findCompanyInfoById(companyId);

        // 임시 테스트를 위한 세션유저 세팅
        User user = (User)session.getAttribute("sessionUser");

        if (user == null) {
            user = userService.findById(1L);
            session.setAttribute("sessionUser", user);
        }

        // 한 유저는 기업에 리뷰를 하나만 달 수 있다.
        // 이미 등록되어 있는 리뷰가 있을 시 등록불가
        List<CompanyReview> companyReviews = companyService.findCompanyReviewByCompanyId(companyId);

        User searchUser = user;
        companyReviews.forEach(companyReview -> {
            boolean isReviewOwner = companyReview.isOwner(searchUser.getUserId());
            if (isReviewOwner) {
                throw new Exception400("계정 하나 당 하나의 기업 리뷰를 등록 할 수 있습니다.");
            }
        });

        CompanyReview saveReview = saveReviewDTO.toEntity(user, companyInfo);
        companyService.companyReviewInsert(saveReview);

        model.addAttribute("companyId", companyId);

        return "redirect:/company/"+companyId+"/reviews";
    }

    @PostMapping("/company/{companyId}/reviews/{reviewId}/delete")
    public String companyReviewDelete(@PathVariable(name = "companyId")Long companyId,
                                        @PathVariable(name = "reviewId")Long reviewId)
    {

        companyService.companyReviewDelete(reviewId);

        return "redirect:/company/"+ companyId +"/reviews";
    }

}
