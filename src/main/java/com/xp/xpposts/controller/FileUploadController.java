package com.xp.xpposts.controller;

import com.xp.xpposts.model.PresignedUrlResponse;
import com.xp.xpposts.service.S3PresignedService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URL;

@RestController
@RequestMapping("/api/expensesrv")
public class FileUploadController {

    private static final String BUCKET_NAME = "vehicle-inspection-images-fms";
    private final S3PresignedService presignedService;

    public FileUploadController(S3PresignedService presignedService) {
        this.presignedService = presignedService;
    }

    @PostMapping("/generate-presigned-url")
    public ResponseEntity<PresignedUrlResponse> generatePresignedUrl(
            @RequestParam String fileName,
            @RequestParam String inspectionType,
            Authentication auth
    ) {
        String objectKey = String.format(
                "vehicles/%s/%s/%d-%s",
                auth.getName(),
                inspectionType,
                System.currentTimeMillis(),
                fileName
        );

        URL url = presignedService.generatePresignedUrl(
                BUCKET_NAME,
                objectKey,
                600
        );

        return ResponseEntity.ok(
                new PresignedUrlResponse(url.toString(), objectKey)
        );
    }
}
