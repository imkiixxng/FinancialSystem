ALTER TABLE Product ADD UNIQUE(ProductName);

DELIMITER //

-- 1. 新增喜好金融商品
CREATE PROCEDURE AddLikeItem(
    IN in_userID VARCHAR(20),
    IN in_productName VARCHAR(100),
    IN in_price DECIMAL(10,2),
    IN in_feeRate DECIMAL(5,4),
    IN in_orderAmount INT,
    IN in_account VARCHAR(20)
)
BEGIN
    DECLARE productNo INT DEFAULT NULL;
    DECLARE totalAmount DECIMAL(12,2);
    DECLARE totalFee DECIMAL(12,2);

    -- 使用 LIMIT 1 且避免 SELECT INTO NULL 問題
    SELECT No INTO productNo FROM Product WHERE ProductName = in_productName LIMIT 1;

    IF productNo IS NULL THEN
        INSERT INTO Product(ProductName, Price, FeeRate)
        VALUES (in_productName, in_price, in_feeRate);
        SET productNo = LAST_INSERT_ID();
    END IF;

    SET totalAmount = in_price * in_orderAmount;
    SET totalFee = totalAmount * in_feeRate;

    INSERT INTO LikeList(UserID, ProductNo, OrderAmount, Account, TotalAmount, TotalFee)
    VALUES (in_userID, productNo, in_orderAmount, in_account, totalAmount, totalFee);
END //

-- 2. 查詢喜好金融商品清單
CREATE PROCEDURE GetUserLikeList(
    IN in_userID VARCHAR(20)
)
BEGIN
    SELECT
        l.SN,
        p.ProductName,
        p.Price,
        p.FeeRate,
        l.OrderAmount,
        l.Account,
        l.TotalAmount,
        l.TotalFee,
        u.Email
    FROM LikeList l
    JOIN Product p ON l.ProductNo = p.No
    JOIN User u ON l.UserID = u.UserID
    WHERE l.UserID = in_userID;
END //

-- 3. 刪除喜好金融商品
CREATE PROCEDURE DeleteLikeItemByProductName(
    IN in_productName VARCHAR(100)
)
BEGIN
    DECLARE productNo INT;

    SELECT No INTO productNo FROM Product WHERE ProductName = in_productName;

    IF productNo IS NOT NULL THEN
        DELETE FROM LikeList WHERE ProductNo = productNo;
        DELETE FROM Product WHERE No = productNo;
    END IF;
END //

-- 4. 更改喜好商品資訊
CREATE PROCEDURE UpdateLikeItem(
    IN in_sn INT,
    IN in_price DECIMAL(10,2),
    IN in_feeRate DECIMAL(5,4),
    IN in_account VARCHAR(20),
    IN in_orderAmount INT
)
BEGIN
    DECLARE productNo INT;
    DECLARE totalAmount DECIMAL(12,2);
    DECLARE totalFee DECIMAL(12,2);

    SELECT ProductNo INTO productNo FROM LikeList WHERE SN = in_sn;

    UPDATE Product
    SET Price = in_price, FeeRate = in_feeRate
    WHERE No = productNo;

    SET totalAmount = in_price * in_orderAmount;
    SET totalFee = totalAmount * in_feeRate;

    UPDATE LikeList
    SET Account = in_account,
        OrderAmount = in_orderAmount,
        TotalAmount = totalAmount,
        TotalFee = totalFee
    WHERE SN = in_sn;
END //

DELIMITER ;
