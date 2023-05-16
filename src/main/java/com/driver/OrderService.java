package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addPartner(String partnerId) {

        DeliveryPartner partner=new DeliveryPartner(partnerId);
        orderRepository.addPartner(partner);
    }

    public void addOrder(Order order) {

        orderRepository.addOrder(order);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        orderRepository.addOrderPartnerPair(orderId,partnerId);

    }

    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return orderRepository.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }


    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        int timeT=orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
        int HH = timeT/60;
        int MM = timeT%60;
        String hh = String.valueOf(HH);
        if( hh.length() == 0 ){
            hh = '0' + hh;
        }
        String mm = String.valueOf(MM);
        if( mm.length() == 0){
            mm = '0' + mm;
        }

        return  hh + ":" + mm;
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}