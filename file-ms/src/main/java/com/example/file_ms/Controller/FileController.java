package com.example.file_ms.Controller;


import com.example.file_ms.Model.FileDocument;
import com.example.file_ms.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileDocument> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        FileDocument savedFile = fileService.storeFile(file);
        return ResponseEntity.ok(savedFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        FileDocument fileDocument = fileService.getFile(id);
        if (fileDocument != null){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDocument.getFileName() + "\"")
                    .body(fileDocument.getData());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable String id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/names")
    public ResponseEntity<Map<Integer, String>> getAllFileNames() {
        Map<Integer, String> fileNames = fileService.getAllFileNames();
        return ResponseEntity.ok(fileNames);
    }


}
