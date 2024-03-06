package br.com.enzohonorato.todolist.service;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.repository.TaskRepository;
import br.com.enzohonorato.todolist.requests.task.*;
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
    //private final ModelMapper modelMapper;

    public SavedTaskResponseBody save(TaskPostRequestBody taskPostRequestBody) {
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskPostRequestBody, Task.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        task.setUser((User) authentication.getPrincipal());

        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, SavedTaskResponseBody.class);
    }

    public List<TaskGetResponseBody> findByUser() {
        ModelMapper modelMapper = new ModelMapper();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Task> taskList = taskRepository.findByUser(user);
        List<TaskGetResponseBody> tasks = taskList.stream()
                .map(task -> modelMapper.map(task, TaskGetResponseBody.class))
                .toList();

        return tasks;
    }

    public List<TaskGetResponseBody> findByUserAndPriority(TaskPriority taskPriority) {
        ModelMapper modelMapper = new ModelMapper();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Task> taskList = taskRepository.findByUserAndPriority(user.getId(), taskPriority.NAME);
        List<TaskGetResponseBody> tasks = taskList.stream()
                .map(task -> modelMapper.map(task, TaskGetResponseBody.class))
                .toList();

        return tasks;
    }

    public List<TaskGetResponseBody> findByUserAndDone(Boolean done) {
        ModelMapper modelMapper = new ModelMapper();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        int bit = done ? 1 : 0;

        List<Task> taskList = taskRepository.findByUserAndDone(user.getId(), bit);
        List<TaskGetResponseBody> tasks = taskList.stream()
                .map(task -> modelMapper.map(task, TaskGetResponseBody.class))
                .toList();

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

        ModelMapper modelMapper = new ModelMapper();
        task = modelMapper.map(taskPutRequestBody, Task.class);
        task.setUser(user);

        taskRepository.save(task);
    }
}
