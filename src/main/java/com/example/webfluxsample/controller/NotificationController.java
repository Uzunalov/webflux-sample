package com.example.webfluxsample.controller;


import com.example.webfluxsample.model.NotificationDto;
import com.example.webfluxsample.service.NotificationService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
  private final NotificationService notificationService;

  @GetMapping
  public Mono<Page<NotificationDto>> getAllNotifications(
      @RequestParam(required = false) String content,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return notificationService.getAllNotifications(content, page, size);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mono<NotificationDto>> getNotification(@PathVariable UUID id) {
    return new ResponseEntity<>(notificationService.getNotification(id), HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
    return notificationService.createNotification(notificationDto);
  }

  @DeleteMapping("/{id}")
  private Mono<Void> deleteNotification(@PathVariable UUID id) {
    return notificationService.deleteNotification(id);
  }
}
