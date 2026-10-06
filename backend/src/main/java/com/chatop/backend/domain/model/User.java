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

  @NotBlank(message = "The name is mandatory.")
  @Size(min = 3, max = 50, message = "The name must contain between 3 and 50 characters.")
  @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\-'’ ]+$", message = "The name contains unauthorized characters.")
  @ToString.Include
  private String name;

  @Column(unique = true)
  @NotBlank(message = "The email is mandatory.")
  @Size(max = 255, message = "The email must not exceed 255 characters.")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$", message = "Invalid email format.")
  @ToString.Include
  private String email;

  @NotBlank(message = "The password is mandatory.")
  @Size(max = 255, message = "The BCrypt hash must not exceed 255 characters.")
  private String password;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
