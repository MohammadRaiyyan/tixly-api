package com.raiyyan.tixly.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id",updatable = false,nullable = false)
  private UUID id;

  @Column(name = "status",nullable = false)
  @Enumerated(EnumType.STRING)
  private TicketStatusEnum status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ticket_type_id")
  private TicketType ticketType;

  @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
  private List<TicketValidation> ticketValidations = new ArrayList<>();

  @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
  private List<QrCode> qrCodes  = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "purchaser_id")
  private User purchaser;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return Objects.equals(id, ticket.id) && status == ticket.status && Objects.equals(createdAt, ticket.createdAt) && Objects.equals(updatedAt, ticket.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, createdAt, updatedAt);
  }
}
