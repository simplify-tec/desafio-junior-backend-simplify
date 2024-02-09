package br.com.enzohonorato.todolist.service;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.repository.TaskRepository;
import br.com.enzohonorato.todolist.requests.task.TaskGetResponseBody;
import br.com.enzohonorato.todolist.requests.task.TaskPostRequestBody;
import br.com.enzohonorato.todolist.requests.task.TaskPriority;
import br.com.enzohonorato.todolist.requests.task.TaskPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public Task save(TaskPostRequestBody taskPostRequestBody) {
        Task task = modelMapper.map(taskPostRequestBody, Task.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        task.setUser((User) authentication.getPrincipal());

        return taskRepository.save(task);
    }

    public List<TaskGetResponseBody> findByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Task> taskList = taskRepository.findByUser(user);
        List<TaskGetResponseBody> tasks = new ArrayList<>();

        for (Task task : taskList) {
            tasks.add(modelMapper.map(task, TaskGetResponseBody.class));
        }

        return tasks;
    }

    public List<TaskGetResponseBody> findByUserAndPriority(TaskPriority taskPriority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Task> taskList = taskRepository.findByUserAndPriority(user.getId(), taskPriority.NAME);
        List<TaskGetResponseBody> tasks = new ArrayList<>();

        for (Task task : taskList) {
            tasks.add(modelMapper.map(task, TaskGetResponseBody.class));
        }

        return tasks;
    }

    public List<TaskGetResponseBody> findByUserAndDone(Boolean done) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        int bit = done ? 1 : 0;

        List<Task> taskList = taskRepository.findByUserAndDone(user.getId(), bit);
        List<TaskGetResponseBody> tasks = new ArrayList<>();

        for (Task task : taskList) {
            tasks.add(modelMapper.map(task, TaskGetResponseBody.class));
        }

        return tasks;
    }

    public void delete(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Task task = taskRepository.findByIdAndUser(id, user.getId());

        if (task == null) {
            throw new RuntimeException("User has no task with the given id");
        }

        taskRepository.delete(task);
    }

    public void update(TaskPutRequestBody taskPutRequestBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Task task = taskRepository.findByIdAndUser(taskPutRequestBody.getId(), user.getId());

        if (task == null) {
            throw new RuntimeException("User has no task with the given id");
        }

        task = modelMapper.map(taskPutRequestBody, Task.class);
        task.setUser(user);

        taskRepository.save(task);
    }
}
