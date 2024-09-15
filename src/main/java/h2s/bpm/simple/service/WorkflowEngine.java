package h2s.bpm.simple.service;


import h2s.bpm.simple.model.ProcessInstance;
import h2s.bpm.simple.repository.ProcessInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowEngine {
    @Autowired
    private ProcessInstanceRepository processInstanceRepository;

    public void executeNextStep(ProcessInstance instance) {
        // Logic to execute the next step in the process
        int nextStep = instance.getCurrentStep() + 1;
        if (nextStep < instance.getProcessDefinition().getSteps().size()) {
            instance.setCurrentStep(nextStep);
            instance.setStatus("IN_PROGRESS");
        } else {
            instance.setStatus("COMPLETED");
        }
        processInstanceRepository.save(instance);
    }
}
