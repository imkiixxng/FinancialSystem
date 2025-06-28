package com.example.finance.controller;

import com.example.finance.dto.*;
import com.example.finance.dto.request.LikeItemAddRequest;
import com.example.finance.dto.request.LikeItemDeleteRequest;
import com.example.finance.dto.request.LikeItemUpdateRequest;
import com.example.finance.service.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like-list")
@CrossOrigin(origins = "*")
public class LikeListController {

    @Autowired
    private LikeListService likeListService;

    // 1. 新增喜好金融商品（使用 JSON 傳輸）
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody com.example.finance.dto.request.LikeItemAddRequest request) {
        likeListService.addLikeItem(request);
        return ResponseEntity.ok("新增成功");
    }

    // 2. 查詢使用者的喜好清單 (GET 不使用 JSON)
    @GetMapping("/{userId}")
    public List<LikeListDTO> getUserLikeList(@PathVariable String userId) {
        return likeListService.getLikeListByUserId(userId);
    }

    // 3. 刪除喜好商品（使用 JSON 傳輸）
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLikeItem(@RequestBody LikeItemDeleteRequest request) {
        boolean result = likeListService.deleteLikeItemByProductName(request.getProductName());
        return result ?
                ResponseEntity.ok("刪除成功") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到該商品");
    }

    // 4. 更新喜好商品資訊（使用 JSON 傳輸）
    @PutMapping("/update")
    public ResponseEntity<?> updateLikeItem(@RequestBody LikeItemUpdateRequest request) {
        try {
            return ResponseEntity.ok(
                    likeListService.updateLikeItem(
                            request.getSn(),
                            request.getPrice(),
                            request.getFeeRate(),
                            request.getAccount(),
                            request.getOrderAmount()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("更新失敗：" + e.getMessage());
        }
    }
}
