package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    Map<String,Order> orderDb=new HashMap<>();
    Map<String,DeliveryPartner>partnerDb=new HashMap<>();
    Map<String,String>orderPartnerDb=new HashMap<>();
    public String addOrder(Order order){
        String id=order.getId();
        orderDb.put(id,order);
        return "Order added successfully";
    }

    public String addPartner(String partnerId){
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        partnerDb.put(partnerId,deliveryPartner);
        return "Partner added successfully";
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
      orderPartnerDb.put(orderId,partnerId);
      return "Order partner added successfully";
    }

    public List<Order> getAllOrders(){
      List<Order>orders=new ArrayList<>();
      for(Order order:orderDb.values())
          orders.add(order);
      return orders;
    }

    public List<DeliveryPartner> getAllPartners(){
        List<DeliveryPartner>deliveryPartners=new ArrayList<>();
        for(DeliveryPartner deliveryPartner:partnerDb.values())
            deliveryPartners.add(deliveryPartner);
        return deliveryPartners;
    }


    public List<String> getAllOrdersPartner(){
        List<String>partner=new ArrayList<>();
        for(String id:orderPartnerDb.values())
            partner.add(id);
        return partner;
    }
}
