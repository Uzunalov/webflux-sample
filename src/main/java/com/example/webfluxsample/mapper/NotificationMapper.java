package com.example.webfluxsample.mapper;

import com.example.webfluxsample.entity.Notification;
import com.example.webfluxsample.model.NotificationDto;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    imports = LocalDateTime.class)
public interface NotificationMapper {

  NotificationDto toNotificationDto(Notification notification);

  @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
  Notification toNotification(NotificationDto notificationDto);
}
