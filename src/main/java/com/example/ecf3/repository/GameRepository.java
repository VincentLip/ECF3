package com.example.ecf3.repository;

import com.example.ecf3.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer> {

    public Game findGameById(int id);

    public Game getGameById(int id);

}
