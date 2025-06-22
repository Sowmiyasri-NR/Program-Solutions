
interface CustomerRepository {
    String findCustomerById(int id);
}

// Step 3: Concrete Repository
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // Simulated customer lookup
        return "Customer{id=" + id + ", name='John Doe'}";
    }
}

// Step 4 & 5: Service Class with Constructor Injection
class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomer(int id) {
        String customer = customerRepository.findCustomerById(id);
        System.out.println("Customer Details: " + customer);
    }
}

// Step 6: Main Class
public class DependencyInjectionExample {
    public static void main(String[] args) {
        // Injecting dependency manually
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);

        // Using the service
        service.displayCustomer(101); // hardcoded customer ID
    }
}
