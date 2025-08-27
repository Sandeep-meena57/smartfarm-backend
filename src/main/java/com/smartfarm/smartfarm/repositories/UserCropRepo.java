package com.smartfarm.smartfarm.repositories;


import com.smartfarm.smartfarm.entity.UserCrop;
import com.smartfarm.smartfarm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCropRepo extends JpaRepository<UserCrop, Long> {
    List<UserCrop> findByUser(User user);
}

