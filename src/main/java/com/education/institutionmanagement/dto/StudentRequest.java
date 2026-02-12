package com.education.institutionmanagement.dto;

public class StudentRequest {
    private String name;
    private Integer grade;
    private Long institutionId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }
    public Long getInstitutionId() { return institutionId; }
    public void setInstitutionId(Long institutionId) { this.institutionId = institutionId; }
}
