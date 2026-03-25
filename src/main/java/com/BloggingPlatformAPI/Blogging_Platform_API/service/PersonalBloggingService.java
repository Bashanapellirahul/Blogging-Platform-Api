package com.BloggingPlatformAPI.Blogging_Platform_API.service;

import com.BloggingPlatformAPI.Blogging_Platform_API.dto.PBRequest;
import com.BloggingPlatformAPI.Blogging_Platform_API.dto.PBResponse;
import com.BloggingPlatformAPI.Blogging_Platform_API.model.PersonalBlogging;
import com.BloggingPlatformAPI.Blogging_Platform_API.repository.PersonalBloggingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalBloggingService {

   private final PersonalBloggingRepository pbRepository;

    // ─── CREATE ────────────────────────────────────────────────────
    public PBResponse generateBlog(PBRequest request){

//        pbRepository.findByTitle(title)
//                .orElseThrow(()-> new RuntimeException("Title not found"));
        //───────── FIND THE TITLE ──────────────────
//        if(pbRepository.existsByTitle(request.getCategory())){
//            throw new RuntimeException("Title not found");
//        }

        // 2. Convert the request DTO → PersonalBlogging entity (model)
        // WHY: We never save DTOs — we always convert to the Model first
        PersonalBlogging entity = new PersonalBlogging();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setCategory(request.getCategory());
        entity.setTags(request.getTags());

        // 3. Save to database
        PersonalBlogging saved = pbRepository.save(entity);
        // 4. Convert saved PersonalBlogging entity → Response DTO and return
        return mapToResponse(saved);
    }

    // ─── Get ALL Titles ────────────────────────────────────────────────────
    public List<PBResponse> getAllBlogTitles(){
//                pbRepository.findByTitle(title)
//                .orElseThrow(()->new RuntimeException("Title not found"));
        // Only return content belonging to THIS title
        return pbRepository.findAll()
                .stream().map(this::mapToResponse)// convert each title to PBResponse
                .collect(Collectors.toList());
    }


    // ─── GET ONE TITLE ────────────────────────────────────────────────────
    public PBResponse getSingleBlog(Long id){
        // findByIdAndTitle = find by ID AND verify it belongs to this title
        // WHY: Security check — title A cannot contain title B's content
        PersonalBlogging personalBlogging = pbRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Title not found or access denied"));

        return mapToResponse(personalBlogging);
    }

    // ─── UPDATE ────────────────────────────────────────────────────
    public PBResponse updateBlog(Long id, PBRequest request){
        PersonalBlogging personalBlogging = pbRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Title not found or access denied"));
        // Update only the fields that came in the request
        personalBlogging.setTitle(request.getTitle());
        personalBlogging.setContent(request.getContent());
        personalBlogging.setCategory(request.getCategory());
        personalBlogging.setTags(request.getTags());
        // Note: createdAt is NOT updated — business rule
        PersonalBlogging updated = pbRepository.save(personalBlogging);
        return mapToResponse(updated);
    }


    public void deleteBlog(Long id){
        PersonalBlogging personalBlogging = pbRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Title not found or access denied"));
        pbRepository.delete(personalBlogging);
    }

    // ─── PRIVATE HELPER — converts PersonalBlogging entity → PBResponse DTO ───
    // WHY private: only this Service uses it — no need to expose it
    // WHY a separate method: we reuse this in ALL methods above — DRY principle
    // DRY = Don't Repeat Yourself — a core real-world coding principle
    private PBResponse mapToResponse(PersonalBlogging entity){
        PBResponse response = new PBResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setCategory(entity.getCategory());
        response.setTags(entity.getTags());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
