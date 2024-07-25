package com.example.file_ms.Service;

import com.example.file_ms.Model.FileDocument;
import com.example.file_ms.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileDocument storeFile(MultipartFile file) throws IOException{
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(file.getOriginalFilename());
        fileDocument.setFileType(file.getContentType());
        fileDocument.setData(file.getBytes());
        fileDocument.setDateAndTime(LocalDateTime.now());

        return fileRepository.save(fileDocument);
    }

    public FileDocument getFile(String id){
        return fileRepository.findById(id).orElse(null);
    }

    public void deleteFile(String id){
        fileRepository.deleteById(id);
    }


    public Map<Integer, String> getAllFileNames() {
        List<FileDocument> files = fileRepository.findAll();
        Map<Integer, String> fileNamesMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            fileNamesMap.put(i + 1, files.get(i).getFileName());
        }
        return fileNamesMap;
    }

}
