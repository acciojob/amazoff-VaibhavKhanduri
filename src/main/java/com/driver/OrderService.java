package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository=new OrderRepository();
    public String addOrder(Order order){
        String ans=orderRepository.addOrder(order);
        return ans;
    }

    public String addPartner(String partnerId){
        String ans=orderRepository.addPartner(partnerId);
        return ans;
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
        return orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){
        List<Order>orders=orderRepository.getAllOrders();
        for (Order order:orders){
            if (order.getId().equals(orderId))
                return order;
        }
        return null;
    }

    public DeliveryPartner getPartnerById(String partnerId){
      List<DeliveryPartner>deliveryPartners=orderRepository.getAllPartners();
      for (DeliveryPartner deliveryPartner:deliveryPartners){
          if (deliveryPartner.getId().equals(partnerId))
              return deliveryPartner;
      }
      return null;
    }

    public int getOrderCountByPartnerId(String partnerId) {
        int count=0;
        List<String>partner=orderRepository.getAllOrdersPartner();
        for (String id:partner)
            if (id.equals(partnerId))
                count++;
        return count;
    }

}
