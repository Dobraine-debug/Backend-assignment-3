package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;
import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;

import java.util.*;


public class SimpleClient {

    public static void main(String[] args) throws CustomerNotFoundException, InterruptedException {
        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application.xml");
        CustomerManagementService service = container.getBean("customerService", CustomerManagementService.class);
        container.close();

    }
}