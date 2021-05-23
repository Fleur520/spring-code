package com.zm.anno.proxy;

public class OrderServiceProxy implements OrderService {

    private OrderServiceImpl orderService  = new OrderServiceImpl();

    @Override
    public void showOrder() {
        System.out.println("----log----");
        orderService.showOrder();
    }
}
