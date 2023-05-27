package com.cos.photogramstart.service;

import com.cos.photogramstart.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;


    @Transactional
    public void likes(int imageId, int principalId) {
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void unLikes(int imageId, int principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }
}
