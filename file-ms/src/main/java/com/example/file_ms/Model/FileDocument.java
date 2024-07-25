package com.example.file_ms.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "files")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDocument {
    @Id
    private String id;
    private String fileName;
    private String fileType;
    private byte[] data;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime DateAndTime;

}
