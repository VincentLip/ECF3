package com.example.ecf3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    @Column(name="date_game")
    private Date gameDate;

    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;

    @OneToOne
    private GameResult gameResult;




}
