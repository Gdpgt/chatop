package com.chatop.backend.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static com.chatop.backend.validation.ValidationPatterns.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @ToString.Include
  private Long id;

  @NotBlank(message = NAME_MANDATORY_MESSAGE)
  @Size(min = NAME_MIN, max = NAME_MAX, message = NAME_SIZE_MESSAGE)
  @Pattern(regexp = NAME_REGEX, message = NAME_FORMAT_MESSAGE)
  @ToString.Include
  private String name;

  @Column(unique = true)
  @NotBlank(message = EMAIL_MANDATORY_MESSAGE)
  @Size(max = EMAIL_MAX, message = EMAIL_SIZE_MESSAGE)
  @Pattern(regexp = EMAIL_REGEX, message = EMAIL_FORMAT_MESSAGE)
  @ToString.Include
  private String email;

  @NotBlank(message = PASSWORD_MANDATORY_MESSAGE)
  @Size(max = 255, message = "The BCrypt hash must not exceed 255 characters.")
  private String password;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
