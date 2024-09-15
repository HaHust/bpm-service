package h2s.bpm.simple.service;

import h2s.bpm.simple.model.ProcessDefinition;
import h2s.bpm.simple.model.ProcessInstance;
import h2s.bpm.simple.repository.ProcessDefinitionRepository;
import h2s.bpm.simple.repository.ProcessInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {
    @Autowired
    private ProcessDefinitionRepository processDefinitionRepository;
    @Autowired
    private ProcessInstanceRepository processInstanceRepository;
    @Autowired
    private WorkflowEngine workflowEngine;

    public ProcessInstance startProcess(String processDefinitionId) {
        ProcessDefinition definition = processDefinitionRepository.findById(Long.parseLong(processDefinitionId))
                .orElseThrow(() -> new RuntimeException("Process definition not found"));
        ProcessInstance instance = new ProcessInstance();
        instance.setProcessDefinition(definition);
        instance.setStatus("STARTED");
        instance.setCurrentStep(0);
        instance = processInstanceRepository.save(instance);
        workflowEngine.executeNextStep(instance);
        return instance;
    }

    public ProcessInstance getProcessInstance(String processInstanceId) {
        return processInstanceRepository.findById(Long.parseLong(processInstanceId))
                .orElseThrow(() -> new RuntimeException("Process instance not found"));
    }
}
