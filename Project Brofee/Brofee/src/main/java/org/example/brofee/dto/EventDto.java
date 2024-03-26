package org.example.brofee.dto;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {
    @NotBlank(message = "The name is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String name;

    private String description;

    @NotBlank(message = "The time is not null.")
    private LocalDateTime timeStart;

    @NotBlank(message = "The time is not null.")
    private LocalDateTime timeEnd;

    @NotBlank(message = "The banner is not null.")
    private MultipartFile bannerUpload;

    private int status;

    @NotBlank(message = "The event code is not null.")
    private String eventCode;

}
