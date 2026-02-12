package com.education.institutionmanagement.controller;

import com.education.institutionmanagement.service.JdbcReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final JdbcReportService reportService;

    public ReportController(JdbcReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/summary")
    public List<Map<String, Object>> getSummaryReport() {
        return reportService.getInstitutionStudentCounts();
    }
}
