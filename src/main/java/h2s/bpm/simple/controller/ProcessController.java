package h2s.bpm.simple.controller;


import h2s.bpm.simple.model.ProcessInstance;
import h2s.bpm.simple.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/processes")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @PostMapping
    public ProcessInstance startProcess(@RequestParam String processDefinitionId) {
        return processService.startProcess(processDefinitionId);
    }

    @GetMapping("/{processInstanceId}")
    public ProcessInstance getProcessInstance(@PathVariable String processInstanceId) {
        return processService.getProcessInstance(processInstanceId);
    }
}