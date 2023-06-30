package com.example.ecf3.repository;

import com.example.ecf3.entity.GameResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameResultRepository extends CrudRepository<GameResult,Integer> {

//    @Query("SELECT GameResult.score from GameResult inner join Game on Game.gameResult.id = GameResult.id")
//    public int  findGameResultScores();





}
