package com.smartfarm.smartfarm.repositories;

import com.smartfarm.smartfarm.entity.Query;
import com.smartfarm.smartfarm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QueryRepo extends JpaRepository<Query,Long> {
//    Fetch queries asked by the specific user
    List<Query> findByUser(User user);

}
