package com.matheus.lab3si.ws.repository;

import com.matheus.lab3si.ws.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Visitor on 1/25/2017.
 */
@Repository
public interface ISubtaskRepository extends JpaRepository<Subtask, Long>{
}
