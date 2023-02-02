package com.fragile.activity_tracker.entity;

import com.fragile.activity_tracker.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    private String  description;

    @Column(name= "time_created")
    private Date createdAt;

    @Column(name= "time_updated")
    private Date  updatedAt;
    private Status status;
   @ManyToOne
   @JoinColumn(  name = "user_id",
           referencedColumnName = "user_id",
           nullable = false,
           foreignKey = @ForeignKey(
                   name = "user_id"
           ) )
   private User users;

//    @ManyToOne
//    @JoinColumn(
//            name = "post_id",
//            referencedColumnName = "id",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "comment_post_id_fk"
//            )
//    )
//    private Post posts;


}
