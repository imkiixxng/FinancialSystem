INSERT INTO User (UserID, UserName, Email, Account) VALUES 
('A1236456789', '王小明', 'test@email.com', '1111999666');

INSERT INTO Product (ProductName, Price, FeeRate) VALUES 
('ETF 高股息', 1000.00, 0.01),
('台積電股票', 600.00, 0.005);

INSERT INTO LikeList (UserID, ProductNo, OrderAmount, Account, TotalAmount, TotalFee) VALUES 
('A1236456789', 1, 10, '1111999666', 10000.00, 100.00);
