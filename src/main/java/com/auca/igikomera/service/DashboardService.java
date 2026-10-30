package com.auca.igikomera.service;

import com.auca.igikomera.model.SaleStatus;
import com.auca.igikomera.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private StallRepository stallRepository;
    @Autowired
    private AnimalSaleRepository animalSaleRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    public Map<String,Long> getSummary(){
        Map<String,Long> summary=new LinkedHashMap<>();
        summary.put("buyers",buyerRepository.count());
        summary.put("sellers",sellerRepository.count());
        summary.put("freeStalls",(long) stallRepository.findFreeStalls().size());
        summary.put("salesToday",animalSaleRepository.countBySaleDate(LocalDate.now()));
        summary.put("inquired", animalSaleRepository.countByStatus(SaleStatus.INQUIRED));
        summary.put("payments",paymentRepository.count());

        return summary;
    }


}
