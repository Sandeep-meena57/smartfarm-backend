package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Query;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    private QueryRepo queryRepo;

    public Query submitQuery(Query query) {
        query.setAskedAt(LocalDateTime.now());
        return queryRepo.save(query);
    }

    public Query getQueryById(Long id) {
        return queryRepo.findById(id).orElseThrow(() -> new RuntimeException("Query not found"));
    }

    public List<Query> getQueriesByUser(User user) {
        return queryRepo.findByUser(user);
    }

    public List<Query> getAllQueries() {
        return queryRepo.findAll();
    }

    public Query updateQuery(Long id, Query updated) {
        Query query = queryRepo.findById(id).orElseThrow(() -> new RuntimeException("Query not found"));
        query.setQuestions(updated.getQuestions());
        query.setAnswers(updated.getAnswers());
        query.setAskedAt(updated.getAskedAt());
        query.setUser(updated.getUser());
        return queryRepo.save(query);
    }

    public void deleteQuery(Long id) {
        queryRepo.deleteById(id);
    }
}
