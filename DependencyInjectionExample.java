public class DependencyInjectionExample {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        Customer customer = service.findCustomerById(101);
        if (customer != null) {
            System.out.println("Customer found:");
            System.out.println("ID   : " + customer.getId());
            System.out.println("Name : " + customer.getName());
        } else {
            System.out.println("Customer not found.");
        }
    }
}

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

interface CustomerRepository {
    Customer findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public Customer findCustomerById(int id) {
        if (id == 101) {
            return new Customer(id, "Alice Johnson");
        }
        return null;
    }
}

class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer findCustomerById(int id) {
        return repository.findCustomerById(id);
    }
}
