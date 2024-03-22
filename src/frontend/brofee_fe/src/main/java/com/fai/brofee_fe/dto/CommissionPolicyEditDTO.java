package com.fai.brofee_fe.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionPolicyEditDTO {

    private Long id;

    @NotNull(message = "Start time is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @NotNull(message = "End time is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @NotNull(message = "Max levels is mandatory")
    @Min(value = 1, message = "Max levels must be greater than 0")
    @Max(value = 10, message = "Max levels must be less than or equal to 10")
    private Integer maxReferralLevels;

    @NotEmpty(message = "Commission tiers is mandatory")
    private List<CommissionTierCreateDTO> commissionTiers;

    private List<Long> serviceIds;

}
