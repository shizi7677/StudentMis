package com.xin.service;

import com.xin.dao.CartMapper;
import com.xin.dao.KindMapper;
import com.xin.dao.OrdersMapper;
import com.xin.dao.QueryMapper;
import com.xin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2016/9/6 0006.
 */

@Service
public class PetServiceImpl implements IPetService {
    @Autowired
    private KindMapper kind_dao;
    @Autowired
    private QueryMapper query_dao;
    @Autowired
    private CartMapper cart_dao;
    @Autowired
    private OrdersMapper order_dao;

    //查找所有宠物种类
    public List<Kind> queryKindList() {
       KindExample example = new KindExample();
        example.createCriteria().andKindidIsNotNull();
        List<Kind> kind_list = kind_dao.selectByExample(example);
        return  kind_list;
    }

    //查询宠物物理信息
    public List<Product> queryProducts(Map map) {
         return query_dao.queryProducts(map);
    }

    //查询宠物们的商品信息
    public List<Item> queryItems(Map map) {
        return query_dao.queryItems(map);
    }

    public List<Cart> addCart(Map map) {
        return cart_dao.addCart(map);
    }

    //删除指定的购物车中的信息
    public List<Cart> deleteCart(CartKey key) {
        List<Cart>  cartList = null;
        if(cart_dao.deleteByPrimaryKey(key)>0) {
            cartList = this.queryCart(key);
        }
        return cartList;
    }

    //根据订单编号来查询特定的订单在购物车中的信息
    public List<Cart> queryCart(CartKey key) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andOrderidEqualTo(key.getOrderid());
        List<Cart> cartList = cart_dao.selectByExample(cartExample);
        return cartList;
    }

    //修改购物车中的信息
    public void updateCart(Map map) {
        cart_dao.updateCart(map);
    }

    //提交保存购物车中信息：为其中的日期赋值
    public boolean submitCart(Orders record) {
        return order_dao.updateByPrimaryKey(record)>0 ? true :false;
    }

}
