package h2s.bpm.simple.service;

import h2s.bpm.simple.model.ActivityDefinition;
import h2s.bpm.simple.model.ProcessDefinition;
import h2s.bpm.simple.repository.ActivityDefinitionRepository;
import h2s.bpm.simple.repository.ProcessDefinitionRepository;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Collection;

@Service
public class BpmnParserService {

    @Autowired
    private ProcessDefinitionRepository processDefinitionRepository;

    @Autowired
    private ActivityDefinitionRepository activityDefinitionRepository;

    @Transactional
    public ProcessDefinition parseBpmnFile(InputStream inputStream, String processName, String version) {
        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(inputStream);

        ProcessDefinition processDefinition = new ProcessDefinition();
        processDefinition.setName(processName);
        processDefinition.setVersion(version);
        processDefinition = processDefinitionRepository.save(processDefinition);

        Collection<FlowNode> flowNodes = modelInstance.getModelElementsByType(FlowNode.class);
        for (FlowNode flowNode : flowNodes) {
            ActivityDefinition activity = new ActivityDefinition();
            activity.setName(flowNode.getName());
            activity.setType(getActivityType(flowNode));
            activity.setProcessDefinition(processDefinition);

            Collection<SequenceFlow> outgoing = flowNode.getOutgoing();
            if (!outgoing.isEmpty()) {
                SequenceFlow nextFlow = outgoing.iterator().next();
                activity.setNextActivityId((nextFlow.getTarget().getId()));
            }

            activityDefinitionRepository.save(activity);
        }

        return processDefinition;
    }

    private String getActivityType(FlowNode flowNode) {
        // Simplified type determination, expand as needed
        if (flowNode instanceof org.camunda.bpm.model.bpmn.instance.Task) {
            return "TASK";
        } else if (flowNode instanceof org.camunda.bpm.model.bpmn.instance.Gateway) {
            return "GATEWAY";
        } else if (flowNode instanceof org.camunda.bpm.model.bpmn.instance.Event) {
            return "EVENT";
        }
        return "UNKNOWN";
    }
}
