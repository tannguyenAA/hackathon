package com.example.demo.controller;

import com.example.demo.controller.request.FieldCreateCommand;
import com.example.demo.controller.request.PitchCreateCommand;
import com.example.demo.entity.Field;
import com.example.demo.entity.Pitch;
import com.example.demo.service.FieldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
@CrossOrigin(origins = "*")
public class FiledController {
    private final FieldService fieldService;

    public FiledController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping
    public Field createField(@RequestBody FieldCreateCommand command, @RequestHeader("UserId") String userId) {
        return fieldService.createField(userId, command);
    }

    @GetMapping
    public List<Field> getField(@RequestHeader("UserId") String userId) {
        return fieldService.getField(userId);
    }

    @PostMapping("/{fieldId}/pitch")
    public Pitch createPitch(@PathVariable("fieldId") String fieldId, @RequestBody PitchCreateCommand command, @RequestHeader("UserId") String userId) {
        return fieldService.createPitch(userId, fieldId, command);
    }

    @GetMapping("/{fieldId}/pitch")
    public List<Pitch> getPitch(@PathVariable("fieldId") String fieldId, @RequestHeader("UserId") String userId) {
        return fieldService.getPitch(userId, fieldId);
    }

}
