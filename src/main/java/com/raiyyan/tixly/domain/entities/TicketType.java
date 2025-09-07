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
@Table(name = "ticket_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketType {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id",nullable = false)
  private UUID id;

  @Column(name = "name",nullable = false)
  private String name;

  @Column(name = "price",nullable = false)
  private Double price;

  @Column(name = "description")
  private String description;

  @Column(name = "total_available")
  private Integer totalAvailable;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Event event;

  @OneToMany(mappedBy = "ticketType",cascade = CascadeType.ALL)
  private List<Ticket> tickets = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    TicketType that = (TicketType) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(totalAvailable, that.totalAvailable) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, description, totalAvailable, createdAt, updatedAt);
  }
}
