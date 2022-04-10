package ru.hh.school.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaryDTO {
    @JsonProperty("salary_from")
    private Integer salaryFrom;

    @JsonProperty("salary_tp")
    private Integer salaryTo;

    @JsonProperty("salary_gross")
    private Boolean salaryGross;

    @NotNull
    @JsonProperty("salary_currency")
    private Boolean salaryCurrency;

    public Integer getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public Boolean getSalaryGross() {
        return salaryGross;
    }

    public void setSalaryGross(Boolean salaryGross) {
        this.salaryGross = salaryGross;
    }

    public Boolean getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(Boolean salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }
}
