package com.example.ToikanaService.controller;

import com.example.ToikanaService.dto.floor.request.FloorRequest;
import com.example.ToikanaService.dto.floor.request.FloorUpdateRequest;
import com.example.ToikanaService.dto.floor.response.FloorResponse;
import com.example.ToikanaService.service.FloorService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*", maxAge = 8600)
public class FloorController {
    final FloorService floorService;
    @PostMapping("/add-floor")
    public FloorResponse addFloor(@RequestBody FloorRequest request){
        return floorService.save(request);
    }

    @GetMapping
    public List<FloorResponse> getAll(){
        return floorService.getAll();
    }
    @GetMapping("/{id}")
    public FloorResponse findById(@PathVariable Long id) {
        return floorService.findById(id);
    }

    @PutMapping("/{id}")
    public Boolean updateFloor(@RequestBody FloorUpdateRequest request, @PathVariable Long id){
        request.setId(id);
        return floorService.updateFloor(request);
    };
}
