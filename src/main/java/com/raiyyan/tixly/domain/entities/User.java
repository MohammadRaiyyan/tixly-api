package com.raiyyan.tixly.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @Column(name = "id", updatable = false,nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private  String name;

  @Column(name = "email", nullable = false)
  private String email;

  @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
  private List<Event> organizedEvents = new ArrayList<>();

  @ManyToMany
  @JoinTable(
          name = "user_attending_events",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "event_id")
  )
  private List<Event> attendingEvents = new ArrayList<>();

  @ManyToMany
  @JoinTable(
          name = "user_staffing_events",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "event_id")
  )
  private List<Event> staffingEvents = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, createdAt, updatedAt);
  }
}
