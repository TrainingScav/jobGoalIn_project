package com.jobgoalin.workinfo.utils;

import com.jobgoalin.workinfo._core.common.PageLink;
import com.jobgoalin.workinfo.company.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompanyInfoViewHelper {
    public static void populateModel(Model model, String keyword, Page<CompanyInfo> companyInfoPage) {
        List<PageLink> pageLinks = IntStream.range(0, companyInfoPage.getTotalPages())
                .mapToObj(i -> new PageLink(i, i + 1, i == companyInfoPage.getNumber()))
                .collect(Collectors.toList());

        Integer previousPageNumber = companyInfoPage.hasPrevious() ? companyInfoPage.getNumber() - 1 : null;
        Integer nextPageNumber = companyInfoPage.hasNext() ? companyInfoPage.getNumber() + 1 : null;

        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("companyInfoPage", companyInfoPage);
        model.addAttribute("pageLinks", pageLinks);
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
    }


}
