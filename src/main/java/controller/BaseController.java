package controller;

import java.util.List;

import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */

/*
 * SOLID - Interface segregation principle: BaseController đang rộng hơn những gì các class con cần thiết
 */

public class BaseController {

    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */

    // Common coupling: checkMediaInCart() sử dụng global data SessionInformation là cartInstance
    public CartItem checkMediaInCart(Media media){
        return SessionInformation.cartInstance.checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */

    // Common coupling: getListCartMedia() sử dụng global data SessionInformation là cartInstance
    public List getListCartMedia(){
        return SessionInformation.cartInstance.getListMedia();
    }
}
