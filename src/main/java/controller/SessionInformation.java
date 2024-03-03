package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
// Common coupling: SessionInformation cung cấp global data nhưng không có thành phần chịu trách nhiệm quản lý dữ liệu
public class SessionInformation {
    public static User mainUser;
    public static Cart cartInstance = new Cart();
    public static LocalDateTime expiredTime;

}
