package h2s.bpm.simple.controller;

import h2s.bpm.simple.model.ProcessDefinition;
import h2s.bpm.simple.service.BpmnParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/bpmn")
public class BpmnController {

    @Autowired
    private BpmnParserService bpmnParserService;

    @PostMapping("/upload")
    public ResponseEntity<ProcessDefinition> uploadBpmnFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("processName") String processName,
            @RequestParam("version") String version) {
        try {
            ProcessDefinition processDefinition = bpmnParserService.parseBpmnFile(file.getInputStream(), processName, version);
            return ResponseEntity.ok(processDefinition);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}