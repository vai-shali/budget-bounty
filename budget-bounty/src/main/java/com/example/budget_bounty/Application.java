/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.budget_bounty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.budget_bounty.model.Admin;
import com.example.budget_bounty.model.Bill;
import com.example.budget_bounty.model.PaymentScheduler;
import com.example.budget_bounty.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    
    User user = new User();
    user.inputUserDetails();
    System.out.println(user);

    Admin admin = new Admin();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        Date dueDate = sdf.parse("2024-08-30");
        Bill bill = new Bill(101, "UPI", 100.0, 10.0, dueDate, user.getId());

        // Admin schedules the bill
        admin.scheduleBill(bill);

        // Admin views scheduled bills
        admin.viewAllScheduledBills();

        // User schedules the payment with the scheduler
        PaymentScheduler scheduler = new PaymentScheduler();
        user.schedulePayment(bill, "2024-08-29", scheduler);

        // Admin processes all payments
        admin.processAllPayments(user);

        System.out.println("Bill Status: " + bill.getBillStatus());
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}