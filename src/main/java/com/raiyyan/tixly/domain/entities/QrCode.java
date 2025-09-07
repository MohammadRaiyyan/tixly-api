package com.raiyyan.tixly.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrCode {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id",nullable = false,updatable = false)
  private UUID id;

  @Column(name = "status",nullable = false)
  @Enumerated(EnumType.STRING)
  private QrCodeStatusEnum status;

  @Column(name = "value",nullable = false)
  private String value;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ticket_id")
  private Ticket ticket;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    QrCode qrCode = (QrCode) o;
    return Objects.equals(id, qrCode.id) && status == qrCode.status && Objects.equals(value, qrCode.value) && Objects.equals(createdAt, qrCode.createdAt) && Objects.equals(updatedAt, qrCode.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, value, createdAt, updatedAt);
  }
}
