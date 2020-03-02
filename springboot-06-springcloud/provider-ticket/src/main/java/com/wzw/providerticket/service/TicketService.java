package com.wzw.providerticket.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public String getTicket(){
        System.out.println("_______8002");
        return "哈哈哈provider-ticket_TicketService";
    }
}
