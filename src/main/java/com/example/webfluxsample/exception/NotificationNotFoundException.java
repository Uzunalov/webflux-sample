package com.example.webfluxsample.exception;

public class NotificationNotFoundException extends Exception {
  public NotificationNotFoundException(String id) {
    super(String.format("Notification not found with id: %s.", id));
  }
}
