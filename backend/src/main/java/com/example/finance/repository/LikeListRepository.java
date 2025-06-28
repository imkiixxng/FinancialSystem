package com.example.finance.repository;

import com.example.finance.model.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeList, Integer> {
    List<LikeList> findByUser_UserID(String userId);
    void deleteByProduct_ProductName(String productName);
}
