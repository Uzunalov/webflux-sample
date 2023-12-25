package com.example.webfluxsample.repository;


import com.example.webfluxsample.entity.Notification;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, UUID> {

  @Query("""
      SELECT * FROM notification.notifications n
      WHERE :content IS NULL OR
      LOWER(n.content) LIKE  LOWER(CONCAT('%', :content, '%'))
      ORDER BY created_at DESC LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}""")
  Flux<Notification> findAllByContentLikeIgnoreCase(String content, Pageable pageable);

  @Query("""
      SELECT COUNT(*) FROM notification.notifications n
      WHERE LOWER(n.content) LIKE LOWER(CONCAT('%', :content, '%'))""")
  Mono<Long> countByContentContainingIgnoreCase(String content);
}
