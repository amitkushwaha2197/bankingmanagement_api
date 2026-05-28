package com.bankingmanagement.model;

import java.util.List;

public record CustomerTO(
        Integer customerId,
        String customerName,
        Integer customerPhone,
        String customerAddress) {
}
