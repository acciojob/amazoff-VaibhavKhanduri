package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> OrderDb=new HashMap<>();
    HashMap<String,DeliveryPartner> PartnerDb=new HashMap<>();

    HashMap<String,String> OrdersPartnerDb=new HashMap<>();

    HashMap<String, List<String>> PartnerOrderDb=new HashMap<>();
    public void addOrder(Order order) {

        String Oid=order.getId();
        OrderDb.put(Oid,order);
    }

    public void addPartner(DeliveryPartner partner) {

        PartnerDb.put(partner.getId(),partner);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return PartnerDb.get(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        if (OrderDb.containsKey(orderId) && PartnerDb.containsKey(partnerId)) {
            OrdersPartnerDb.put(orderId, partnerId);
            List<String> list = new ArrayList<>();

            if (PartnerOrderDb.containsKey(partnerId)) {
                list = PartnerOrderDb.get(partnerId);
                list.add(orderId);
            }
            PartnerOrderDb.put(partnerId, list);
            DeliveryPartner deliveryPartner=PartnerDb.get(partnerId);
            deliveryPartner.setNumberOfOrders(list.size());
        }
    }

    public Order getOrderById(String orderId) {

        return OrderDb.get(orderId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        DeliveryPartner partner=PartnerDb.get(partnerId);
        return partner.getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return PartnerOrderDb.get(partnerId);
    }

    public List<String> getAllOrders() {
        return new ArrayList<>(OrderDb.keySet());
    }


    public Integer getCountOfUnassignedOrders() {
        return OrderDb.size()-OrdersPartnerDb.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        List<String> list = Arrays.asList(time.split(":"));
        int hh = Integer.parseInt(list.get(0));
        int mm = Integer.parseInt(list.get(1));
        int timeF= (hh * 60 + mm);
        int count=0;
        List<String>orders=PartnerOrderDb.get(partnerId);

        for(String orderId:orders)
        {
            int currentTime=OrderDb.get(orderId).getDeliveryTime();
            if(currentTime > timeF)
            {
                count++;
            }
        }
        return count;
    }

    public int getLastDeliveryTimeByPartnerId(String partnerId) {
        int maxTime=0;
        List<String>orders=PartnerOrderDb.get(partnerId);
        for(String orderId:orders)
        {
            int currentTime=OrderDb.get(orderId).getDeliveryTime();
            maxTime=Math.max(maxTime,currentTime);
        }
        return maxTime;
    }

    public void deletePartnerById(String partnerId) {
        PartnerDb.remove(partnerId);
        List<String>orders=PartnerOrderDb.get(partnerId);
        PartnerOrderDb.remove(partnerId);

        for(String orderId:orders)
        {
            OrdersPartnerDb.remove(orderId);
        }
    }

    public void deleteOrderById(String orderId) {
        OrderDb.remove(orderId);

        String partnerId=OrdersPartnerDb.get(orderId);
        OrdersPartnerDb.remove(partnerId);

        PartnerOrderDb.get(partnerId).remove(orderId);

        PartnerDb.get(partnerId).setNumberOfOrders(PartnerOrderDb.get(partnerId).size());
    }
}