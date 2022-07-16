package com.example.ToikanaService.controller;

import com.example.ToikanaService.dto.sewer.request.SewerRequest;
import com.example.ToikanaService.dto.sewer.request.SewerUpdateRequest;
import com.example.ToikanaService.dto.sewer.response.SewerResponse;
import com.example.ToikanaService.service.SewerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sewer")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*", maxAge = 8600)
public class SewerController {
    final SewerService sewerService;

    @PostMapping("/add-sewer")
    public SewerResponse save(@RequestBody SewerRequest request){
        return sewerService.save(request);
    }
    @GetMapping
    public List<SewerResponse> getAll(){
        return sewerService.getAll();
    }
    @PutMapping("/{id}")
    public Boolean update(@RequestBody SewerUpdateRequest request, @PathVariable Long id){
        request.setId(id);
        return sewerService.updateSewer(request);
    };
}
