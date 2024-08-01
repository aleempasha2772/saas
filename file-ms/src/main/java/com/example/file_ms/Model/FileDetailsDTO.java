package com.example.file_ms.Model;

import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDetailsDTO {
    private Integer id;
    private String fileName;
    private String fileType;
    private LocalDateTime uploadDateTime;
}
