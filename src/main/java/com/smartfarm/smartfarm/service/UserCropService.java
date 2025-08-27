package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.Crop;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.entity.UserCrop;
import com.smartfarm.smartfarm.repositories.CropRepo;
import com.smartfarm.smartfarm.repositories.UserCropRepo;
import com.smartfarm.smartfarm.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCropService {

    private final UserCropRepo userCropRepo;
    private final UserRepo userRepo;
    private final CropRepo cropRepo;

    public UserCrop createUserCrop(Long cropId, double areaInAcres, String notes, LocalDate sowingDate) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow();
        Crop crop = cropRepo.findById(cropId).orElseThrow();

        UserCrop userCrop = UserCrop.builder()
                .user(user)
                .crop(crop)
                .areaInAcres(areaInAcres)
                .sowingDate(sowingDate)
                .notes(notes)
                .build();

        return userCropRepo.save(userCrop);
    }

    public List<UserCrop> getMyCrops() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow();
        return userCropRepo.findByUser(user);
    }

    public void deleteUserCrop(Long id) {
        userCropRepo.deleteById(id);
    }
}
