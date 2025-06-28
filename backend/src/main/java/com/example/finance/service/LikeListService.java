package com.example.finance.service;

import com.example.finance.dto.LikeListDTO;
import com.example.finance.model.LikeList;
import com.example.finance.model.Product;
import com.example.finance.model.User;
import com.example.finance.repository.LikeListJdbcRepository;
import com.example.finance.repository.LikeListRepository;
import com.example.finance.repository.ProductRepository;
import com.example.finance.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeListService {

    @Autowired
    private LikeListRepository likeListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 1. 新增喜好金融商品（呼叫 Stored Procedure）
    @Autowired
    private LikeListJdbcRepository jdbcRepo;

    public void addLikeItem(com.example.finance.dto.request.LikeItemAddRequest request) {
        jdbcRepo.callAddLikeItem(
                request.getUserId(),
                request.getProductName(),
                request.getPrice(),
                request.getFeeRate(),
                request.getOrderAmount(),
                request.getAccount()
        );
    }

    // 2. 查詢使用者的所有喜好金融商品清單（回傳 DTO）
    public List<LikeListDTO> getLikeListByUserId(String userId) {
        List<LikeList> likeLists = likeListRepository.findByUser_UserID(userId);
        return likeLists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 3. 根據產品名稱刪除使用者的喜好金融商品清單
    @Transactional
    public boolean deleteLikeItemByProductName(String productName) {
        Optional<Product> productOpt = productRepository.findByProductName(productName);
        if (productOpt.isPresent()) {
            likeListRepository.deleteByProduct_ProductName(productName);
            productRepository.deleteByProductName(productName);
            return true;
        }
        return false;
    }

    // 4. 更新喜好金融商品資訊（包含價格、費率、帳號、數量）
    @Transactional
    public LikeList updateLikeItem(Integer sn, BigDecimal price, BigDecimal feeRate,
                                   String account, Integer orderAmount) {
        LikeList likeItem = likeListRepository.findById(sn)
                .orElseThrow(() -> new RuntimeException("找不到 LikeList"));

        Product product = likeItem.getProduct();
        product.setPrice(price);
        product.setFeeRate(feeRate);
        productRepository.save(product);

        // 更新 LikeList
        likeItem.setAccount(account);
        likeItem.setOrderAmount(orderAmount);
        BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(orderAmount));
        BigDecimal totalFee = totalAmount.multiply(feeRate);
        likeItem.setTotalAmount(totalAmount);
        likeItem.setTotalFee(totalFee);

        return likeListRepository.save(likeItem);
    }

    // 將 LikeList 轉換為 DTO
    private LikeListDTO convertToDTO(LikeList likeItem) {
        return new LikeListDTO(
                likeItem.getSn(),
                likeItem.getProduct().getProductName(),
                likeItem.getProduct().getPrice(),
                likeItem.getProduct().getFeeRate(),
                likeItem.getOrderAmount(),
                likeItem.getAccount(),
                likeItem.getTotalAmount(),
                likeItem.getTotalFee(),
                likeItem.getUser().getEmail()
        );
    }
}
