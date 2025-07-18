package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo._core.common.PageLink;
import com.jobgoalin.workinfo._core.errors.exception.Exception400;
import com.jobgoalin.workinfo._core.errors.exception.Exception403;
import com.jobgoalin.workinfo._core.errors.exception.Exception404;
import com.jobgoalin.workinfo._core.errors.exception.Exception500;
import com.jobgoalin.workinfo.user.CompUser;
import com.jobgoalin.workinfo.user.LoginUser;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserService;
import com.jobgoalin.workinfo.utils.CompanyInfoViewHelper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompanyController {


    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;
    private final UserService userService;
    

    //전체 게시글 조회 and 제목 검색한 게시글 조회
    @GetMapping("/company/list")
    public String companyInfoList(Model model, HttpSession session,
                                  @RequestParam(required = false) String keyword,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        LoginUser loginUser = (LoginUser) session.getAttribute("sessionUser");
        Pageable pageable = PageRequest.of(page, size, Sort.by("instDate").descending());
        Page<CompanyInfo> companyInfoPage;

        if (keyword == null) {
            log.info(">> 기업정보 목록 조회 시작 << ");
            if (loginUser != null) {
                CompanyInfo canWrite = companyService.findCompanyInfoByUserId(loginUser.getId());
                if (canWrite == null) {
                    model.addAttribute("isCompanyInfoWritable", true);
                }
            }
            companyInfoPage = companyService.findAllCompanyInfo(pageable);
        } else {
            companyInfoPage = companyService.findCompanyNamesByKeyword(keyword, pageable);
        }

        CompanyInfoViewHelper.populateModel(model, keyword, companyInfoPage);

        return "company/company_list";
    }


    @GetMapping("/company/{id}")
    public String companyInfoDetail(@PathVariable(name = "id") Long id, Model model, HttpSession session) {

        log.info(">> 기업 상세정보 화면이동 및 조회 시작 << ");

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");
        CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);

        if (user != null) {
            // 등록 버튼 표시여부 (기업정보가 이미 등록 되어 있을 시 버튼 표시 삭제)
            if (companyInfoDetail.getCompUser().getCompUserId().equals(user.getId())) {
                model.addAttribute("isCompanyInfoUpdateAndDeleteYn", true);
            }
        }

        model.addAttribute("companyInfo", companyInfoDetail);

        return "company/company_detail";
    }

    @GetMapping("/company/form")
    public String companyInfoForm(HttpSession session) {

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");

        if (!user.isCompany()) {
            throw new Exception403("기업 회원만 등록 할 수 있습니다.");
        }

        CompanyInfo searchCompanyInfoByCompUserId = companyService.findCompanyInfoByUserId(user.getId());

        if (searchCompanyInfoByCompUserId != null) {
            throw new Exception400("기업정보 등록한 기업 회원 당 하나만 작성 가능합니다.");
        }

        log.info(">> 기업정보 작성화면 이동 << ");

        return "company/company_form";
    }

    @PostMapping("/company/form")
    public String companyInfoInsert(CompanyRequest.SaveDTO saveDTO, HttpSession session) {

        log.info(">> 기업정보 등록 시작 << ");

        // CompUser 객체에 세션유저 id 담아줌.
        LoginUser user = (LoginUser) session.getAttribute("sessionUser");
        CompUser compUser = userService.findCompUserById(user.getId());

        CompanyInfo companyInfo = companyService.companyInfoInsert(saveDTO.toEntity(compUser));

        if (companyInfo == null) {
            throw new Exception500("등록 처리 중 에러가 발생했습니다.");
        }

        return "redirect:/company/list";
    }

    @GetMapping("/company/{id}/update")
    public String companyInfoUpdateForm(@PathVariable(name = "id") Long id, Model model, HttpSession session) {

        log.info(">> 기업정보 수정 화면이동 및 조회 시작 << ");

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");
        CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);

        if (!companyInfoDetail.getCompUser().getCompUserId().equals(user.getId())) {
            throw new Exception403("해당 기업의 회원만 수정 할 수 있습니다.");
        }

        model.addAttribute("companyInfo", companyInfoDetail);

        return "company/company_update";
    }

    @PostMapping("/company/{id}/update")
    public String companyInfoUpdate(@PathVariable(name = "id") Long id, CompanyRequest.SaveDTO saveDTO, HttpSession session) {

        log.info(">> 기업정보 수정 시작 << ");

        // CompUser 객체에 세션유저 id 담아줌.
        LoginUser user = (LoginUser) session.getAttribute("sessionUser");
        CompUser compUser = userService.findCompUserById(user.getId());

        CompanyInfo companyInfo = companyService.companyInfoUpdate(id, saveDTO.toEntity(compUser));
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

    // 전체 리뷰 목록
    @GetMapping("/company/{id}/reviews")
    public String companyReview(@PathVariable(name = "id") Long companyId,
                                @RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "10")int size, Model model, HttpSession session)  {

        LoginUser user = (LoginUser)session.getAttribute("sessionUser");
        Pageable pageable = PageRequest.of(page ,size, Sort.by("instDate").descending());

        Page<CompanyReview> companyReviews = companyService.findCompanyReviewByCompanyId(pageable,companyId);

        // 페이지 네비게이션 용 데이터 준비
        List<PageLink> pageLinks = new ArrayList<>();
        for(int i = 0; i < companyReviews.getTotalPages(); i++) {
            pageLinks.add(new PageLink(i, i + 1, i == companyReviews.getNumber()));
        }

        Integer previousPageNumber = companyReviews.hasPrevious() ? companyReviews.getNumber() -1 : null;
        Integer nextPageNumber = companyReviews.hasNext() ? companyReviews.getNumber() + 1 : null;

        // 뷰 화면에 데이터 전달
        model.addAttribute("companyReviews", companyReviews);
        // 페이지 네비게이션에 사용할 번호 링크 리스트
        model.addAttribute("pageLinks", pageLinks);
        // 이전 페이지 번호 (없으면 null)
        model.addAttribute("previousPageNumber", previousPageNumber);
        // 다음 페이지 번호 (없으면 null)
        model.addAttribute("nextPageNumber", nextPageNumber);
        // 리뷰 소유권 설정 (삭제 버튼 표시용)
        if (user != null) {
            if (!user.isCompany()) {
                companyReviews.forEach(companyReview -> {
                    boolean isReviewOwner = companyReview.isOwner(user.getId());
                    companyReview.setIsMyReview(isReviewOwner);
                    log.info("isMyReview 확인 : {}", companyReview.getIsMyReview());
                });

                // 이전에 리뷰 작성을 하였는지 확인하여 작성 했을 시 등록 버튼 숨김
                Long findReviewById = companyService.countReviewsByUserId(user.getId());
                boolean isWritable = (findReviewById == null || findReviewById <= 0);

                model.addAttribute("isWritable", isWritable);
            }
        }

        log.info("리뷰 개수 확인 : {}", companyReviews.getSize());

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
        LoginUser sessionUser = (LoginUser)session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());

        if (user == null) {
            throw new Exception404("유저 정보를 찾을 수 없습니다.");
        }

        // 한 유저는 기업에 리뷰를 하나만 달 수 있다.
        // 이미 등록되어 있는 리뷰가 있을 시 등록불가
        List<CompanyReview> companyReviews = companyService.findCompanyReviewByCompanyId(companyId);

        companyReviews.forEach(companyReview -> {
            boolean isReviewOwner = companyReview.isOwner(user.getUserId());
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
