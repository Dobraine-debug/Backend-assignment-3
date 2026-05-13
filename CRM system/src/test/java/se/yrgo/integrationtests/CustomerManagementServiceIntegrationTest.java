package se.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration({"/application.xml", "/datasource-test.xml"})
@Transactional
@ExtendWith(SpringExtension.class)
public class CustomerManagementServiceIntegrationTest {
    @Autowired
    private CustomerManagementService service;

    @Test
    public void testFindCustomerById() throws CustomerNotFoundException {
        Customer testCustomer =
                new Customer("T101", "TestCompany", "test@mail.com", "0733-112233", "Testing");
        service.newCustomer(testCustomer);
        Customer foundCustomer = service.findCustomerById("T101");
        assertEquals(testCustomer, foundCustomer);
    }

    @Test
    public void testUpdateCustomer() throws CustomerNotFoundException {
        Customer testCustomer =
                new Customer("T101", "TestCompany", "test@mail.com", "0733-112233", "Testing");
        service.newCustomer(testCustomer);
        Customer updatedCustomer =
                new Customer("T101", "TestCompany", "test@mail.com", "0733-112233", "Updated test");
        service.updateCustomer(updatedCustomer);
        Customer foundCustomer = service.findCustomerById("T101");
        assertEquals(updatedCustomer, foundCustomer);
    }

    @Test
    public void findCustomerByName(){
        Customer testCustomer =
                new Customer("T101", "TestCompany", "test@mail.com", "0733-112233", "Testing");
        service.newCustomer(testCustomer);
        List<Customer> foundCustomer = service.findCustomersByName("TestCompany");
        assertEquals(testCustomer, foundCustomer.getFirst());

    }
    @Test
    public void findAllCustomers(){
        Customer testCustomer =
                new Customer("T101", "TestCompany", "test@mail.com", "0733-112233", "Testing");
        service.newCustomer(testCustomer);
        service.newCustomer(testCustomer);
        service.newCustomer(testCustomer);
        service.newCustomer(testCustomer);
        service.newCustomer(testCustomer);
        List<Customer> foundCustomers = service.getAllCustomers();
        assertEquals(testCustomer, foundCustomers.getFirst());
    }
    @Test
    public void addCall() throws CustomerNotFoundException {
        Customer testCustomer =
                new Customer("T101", "TestCompany", "test@mail.com", "0733-112233", "Testing");
        Date timestamp = java.sql.Date.valueOf(LocalDate.now());
        Call testCall = new Call("This is a test", timestamp);
        service.newCustomer(testCustomer);
        service.recordCall("T101", testCall);
        Customer foundCustomer = service.getFullCustomerDetail("T101");
        Call foundCall = foundCustomer.getCalls().getFirst();
        assertEquals(testCall, foundCall);
    }
}
