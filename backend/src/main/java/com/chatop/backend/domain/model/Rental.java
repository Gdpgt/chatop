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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "rentals")
@Getter
@Setter
@Accessors(chain = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @ToString.Include
  private Integer id;

  @NotBlank(message = NAME_MANDATORY_MESSAGE)
  @Size(min = RENTAL_NAME_MIN, max = RENTAL_NAME_MAX, message = RENTAL_NAME_SIZE_MESSAGE)
  @Pattern(regexp = SINGLE_LINE_TEXT_REGEX, message = RENTAL_NAME_FORMAT_MESSAGE)
  @ToString.Include
  private String name;

  @NotNull(message = SURFACE_MANDATORY_MESSAGE)
  @Min(value = SURFACE_MIN, message = SURFACE_RANGE_MESSAGE)
  @Max(value = SURFACE_MAX, message = SURFACE_RANGE_MESSAGE)
  @ToString.Include
  private Integer surface;

  @NotNull(message = PRICE_MANDATORY_MESSAGE)
  @Min(value = PRICE_MIN, message = PRICE_RANGE_MESSAGE)
  @Max(value = PRICE_MAX, message = PRICE_RANGE_MESSAGE)
  @ToString.Include
  private Integer price;

  @NotBlank(message = PICTURE_MANDATORY_MESSAGE)
  @Size(max = PICTURE_MAX, message = PICTURE_SIZE_MESSAGE)
  private String picture;

  @NotBlank(message = DESCRIPTION_MANDATORY_MESSAGE)
  @Size(max = DESCRIPTION_MAX, message = DESCRIPTION_SIZE_MESSAGE)
  @Pattern(regexp = FREE_TEXT_REGEX, message = DESCRIPTION_FORMAT_MESSAGE)
  private String description;

  @Column(name = "owner_id", nullable = false)
  @NotNull(message = OWNER_MANDATORY_MESSAGE)
  @ToString.Include
  private Integer ownerId;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
