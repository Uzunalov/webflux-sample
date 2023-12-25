package com.example.webfluxsample.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "notifications", schema = "notification")
public class Notification {
  @Id
  private UUID id;
  private String content;
  private LocalDateTime createdAt;
}
