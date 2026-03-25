package com.BloggingPlatformAPI.Blogging_Platform_API.controller;

import com.BloggingPlatformAPI.Blogging_Platform_API.dto.PBRequest;
import com.BloggingPlatformAPI.Blogging_Platform_API.dto.PBResponse;
import com.BloggingPlatformAPI.Blogging_Platform_API.service.PersonalBloggingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@RequiredArgsConstructor
public class PersonalBloggingController {
    private final PersonalBloggingService pbService;

    @PostMapping
    public ResponseEntity<PBResponse> createBlog(@Valid @RequestBody PBRequest pbRequest){
//        PBResponse response = pbService.generateBlog(pbRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pbService.generateBlog(pbRequest));
    }

    @GetMapping
    public ResponseEntity<List<PBResponse>> getAllBlogs(){
//        List<PBResponse> titles = pbService.getAllBlogTitles();
        return ResponseEntity.ok(pbService.getAllBlogTitles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PBResponse> getBlogById(@PathVariable Long id){
//        PBResponse response = pbService.getSingleBlog(id);
        return  ResponseEntity.ok(pbService.getSingleBlog(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PBResponse> updateBlogById(@PathVariable Long id,@Valid @RequestBody PBRequest pbRequest){
//        PBResponse response = pbService.updateBlog(id, pbRequest);
        return ResponseEntity.ok(pbService.updateBlog(id, pbRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PBResponse> deleteBlogById(@PathVariable Long id){
        pbService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }



}
