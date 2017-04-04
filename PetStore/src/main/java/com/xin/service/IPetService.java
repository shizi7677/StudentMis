package com.xin.service;

import com.xin.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2016/9/6 0006.
 */
public interface IPetService {
    List<Kind> queryKindList();

    List<Product> queryProducts(Map map);
    List<Item> queryItems(Map map);

    List<Cart> addCart(Map map);
    void updateCart(Map map);   /*修改购物车中的信息*/

    List<Cart> deleteCart(CartKey key);
    List<Cart> queryCart(CartKey key);

    boolean submitCart(Orders record);
}
