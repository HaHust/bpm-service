# Business Process Management (BPM) System

## Overview
This BPM system is designed to manage and execute business processes defined using BPMN (Business Process Model and Notation). It provides APIs for uploading BPMN files, managing process instances, and handling tasks within those processes.

## System Components

### 1. API Endpoints

#### BPMN Upload
- **Endpoint**: POST `/api/bpmn/upload`
- **Description**: Uploads a BPMN file and creates a process definition
- **Parameters**:
  - `file`: BPMN file (MultipartFile)
  - `processName`: Name of the process
  - `version`: Version of the process

#### Task Management
- **Create Task**: POST `/api/tasks`
- **Get Task**: GET `/api/tasks/{taskId}`
- **Get All Tasks**: GET `/api/tasks`
- **Update Task**: PUT `/api/tasks/{taskId}`
- **Complete Task**: POST `/api/tasks/{taskId}/complete`
- **Get Tasks by Process Instance**: GET `/api/tasks/process/{processInstanceId}`

### 2. Services

#### BpmnParserService
- Parses BPMN files
- Creates ProcessDefinition and ActivityDefinition entities

#### ProcessService
- Manages process instances
- Starts and monitors process execution

#### TaskService
- Creates and manages tasks
- Handles task assignment and completion

### 3. Entity Tables

#### ProcessDefinition
- `id`: Long (Primary Key)
- `name`: String
- `description`: String
- `version`: String

#### ActivityDefinition
- `id`: Long (Primary Key)
- `name`: String
- `type`: String (e.g., "TASK", "GATEWAY", "EVENT")
- `processDefinition`: ProcessDefinition (Many-to-One)
- `nextActivityId`: Long

#### ProcessInstance
- `id`: Long (Primary Key)
- `processDefinition`: ProcessDefinition (Many-to-One)
- `status`: String
- `startTime`: LocalDateTime
- `endTime`: LocalDateTime
- `currentActivityId`: Long

#### TaskInstance
- `id`: Long (Primary Key)
- `processInstance`: ProcessInstance (Many-to-One)
- `activityDefinition`: ActivityDefinition (Many-to-One)
- `status`: String
- `assignee`: String
- `startTime`: LocalDateTime
- `endTime`: LocalDateTime

#### Variable
- `id`: Long (Primary Key)
- `name`: String
- `value`: String
- `type`: String
- `processInstance`: ProcessInstance (Many-to-One)
- `taskInstance`: TaskInstance (Many-to-One)

#### AuditLog
- `id`: Long (Primary Key)
- `processInstance`: ProcessInstance (Many-to-One)
- `action`: String
- `timestamp`: LocalDateTime
- `userId`: String
- `details`: String

## Usage Examples

### Uploading a BPMN File
```bash
curl -X POST \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/your/bpmn/file.bpmn" \
  -F "processName=SampleProcess" \
  -F "version=1.0" \
  http://localhost:8080/api/bpmn/upload
```

### Creating a Task
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"processInstanceId": 1, "activityDefinitionId": 1, "assignee": "John Doe"}' \
  http://localhost:8080/api/tasks
```

### Completing a Task
```bash
curl -X POST http://localhost:8080/api/tasks/1/complete
```

## Setup and Configuration

1. Ensure you have Java and Maven installed.
2. Clone the repository.
3. Configure the database connection in `application.properties`.
4. Run `mvn spring-boot:run` to start the application.

## Dependencies

- Spring Boot
- Spring Data JPA
- Camunda BPMN Model API
- PostgreSQL (or your preferred database)

## Future Enhancements

- Implement user authentication and authorization
- Add support for more complex BPMN elements
- Develop a user interface for process and task management
- Integrate with external systems through API calls

