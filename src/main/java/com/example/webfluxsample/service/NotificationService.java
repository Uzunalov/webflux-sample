package com.example.webfluxsample.service;


import com.example.webfluxsample.exception.NotificationNotFoundException;
import com.example.webfluxsample.mapper.NotificationMapper;
import com.example.webfluxsample.model.NotificationDto;
import com.example.webfluxsample.repository.NotificationRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final NotificationMapper notificationMapper;
  private final NotificationRepository notificationRepository;

  public Mono<Page<NotificationDto>> getAllNotifications(String content, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return notificationRepository
        .findAllByContentLikeIgnoreCase(content, pageable)
        .map(notificationMapper::toNotificationDto)
        .collectList()
        .zipWith(notificationRepository.countByContentContainingIgnoreCase(content))
        .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
  }

  public Mono<NotificationDto> getNotification(UUID id) {
    return notificationRepository.findById(id)
        .map(notificationMapper::toNotificationDto)
        .switchIfEmpty(Mono.error(new NotificationNotFoundException(id.toString())));
  }

  public Mono<NotificationDto> createNotification(NotificationDto notificationDto) {
    return notificationRepository.save(notificationMapper.toNotification(notificationDto))
        .map(notificationMapper::toNotificationDto);
  }

  public Mono<Void> deleteNotification(UUID id) {
    return notificationRepository.deleteById(id);
  }
}
