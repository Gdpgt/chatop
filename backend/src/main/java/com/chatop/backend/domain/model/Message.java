package com.chatop.backend.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import static com.chatop.backend.validation.ValidationPatterns.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
@Accessors(chain = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @ToString.Include
  private Integer id;

  @Column(name = "rental_id", nullable = false)
  @NotNull(message = RENTAL_MANDATORY_MESSAGE)
  @ToString.Include
  private Integer rentalId;

  @Column(name = "user_id", nullable = false)
  @NotNull(message = SENDER_MANDATORY_MESSAGE)
  @ToString.Include
  private Integer userId;

  @NotBlank(message = MESSAGE_MANDATORY_MESSAGE)
  @Size(max = MESSAGE_MAX, message = MESSAGE_SIZE_MESSAGE)
  @Pattern(regexp = FREE_TEXT_REGEX, message = MESSAGE_FORMAT_MESSAGE)
  private String message;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
