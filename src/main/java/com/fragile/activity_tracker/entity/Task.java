package com.fragile.activity_tracker.entity;

import com.fragile.activity_tracker.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task")
@Builder
public class Task implements Comparable<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    private String  description;

    @Column(name= "time_created")
    private LocalDateTime createdAt;

    @Column(name= "time_updated")
    private LocalDateTime  updatedAt;

    @Column(name= "time_completed")
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    private Status status;
   @ManyToOne
   @JoinColumn(  name = "user_id",
           referencedColumnName = "user_id",
           nullable = false,
           foreignKey = @ForeignKey(
                   name = "user_id"
           ) )
   private User users;



    @Override
    public int compareTo(Task o) {
        return (int) (id - o.getId());
    }




}
