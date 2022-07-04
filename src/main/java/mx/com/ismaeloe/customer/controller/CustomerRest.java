package mx.com.ismaeloe.customer.controller;

//Sin Service porque invoca a un MicroServicio
//import mx.com.ismaeloe.customer.service.CustomerService;
import mx.com.ismaeloe.customer.repository.entity.Customer;
import mx.com.ismaeloe.customer.repository.entity.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRest {

	private final static Logger LOG = LoggerFactory.getLogger(CustomerRest.class);

	/*
	 *  Best WebClient for Production
	 */
	RestTemplate restTemplate = new RestTemplate();

	/**
	 * TODO Inject a property value
	 * @Value("${client.url}")
	 * private String url;
	 */
    private String url = "http://localhost:8091/customers/";

	@GetMapping(value = "/health")
	public String healt() {
		return "Customer Hystrix health is ok";
	}
    // -------------------Retrieve All Customers--------------------------------------------

/*
	    @GetMapping(value = "/hystrix")
	    public List<Employee> hystrix() {
	        return restTemplate.getForObject(url, List.class);
	    }
*/

    public List<Customer> breakerMethod(Long regionId)
    {
        return Collections.emptyList();
    }

    
	//com.netflix.hystrix.contrib.javanica.exception.FallbackDefinitionException: fallback method wasn't found: metodoRespaldo([class java.lang.Long])
    //Does not exist. (Notice the argument in the signature)
	//When you define a fallback method with that annotation the fallback method must match the same parameters of the method where you define the Hystrix Command

    @HystrixCommand(groupKey = "cayo", commandKey = "cayo", fallbackMethod = "breakerMethod") //
    @GetMapping
//ok public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(name = "regionId" , required = false) Long regionId )
     public List<Customer> listAllCustomers(@RequestParam(name = "regionId" , required = false) Long regionId )
    {
    	LOG.warn("listAllCustomers url ={}" ,url );
    	
        List<Customer> customers =  new ArrayList<>();
    
        //FALTA cuando hay Region
        if (null ==  regionId) {
        	/*
            customers = customerService.findCustomerAll();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            */
            return restTemplate.getForObject(url, List.class);
        }
        /*
        else{
            Region Region= new Region();
            Region.setId(regionId);
            customers = customerService.findCustomersByRegion(Region);
            if ( null == customers ) {
                LOG.error("Customers with Region id {} not found.", regionId);
                return  ResponseEntity.notFound().build();
            }
        } */

        //return  ResponseEntity.ok(customers);
        return customers;
    }

    
	 /* FALTA INSERT, UPDATE, DELETE
      * 
	 // -------------------Retrieve Single Customer------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        LOG.info("Fetching Customer with id {}", id);
        Customer customer = customerService.getCustomer(id);
        if (  null == customer) {
            LOG.error("Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(customer);
    }


    // -------------------Create a Customer-------------------------------------------

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        LOG.info("Creating Customer : {}", customer);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

       Customer customerDB = customerService.createCustomer (customer);

        return  ResponseEntity.status( HttpStatus.CREATED).body(customerDB);
    }

    // ------------------- Update a Customer ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        LOG.info("Updating Customer with id {}", id);

        Customer currentCustomer = customerService.getCustomer(id);

        if ( null == currentCustomer ) {
            LOG.error("Unable to update. Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer=customerService.updateCustomer(customer);
        return  ResponseEntity.ok(currentCustomer);
    }

    // ------------------- Delete a Customer-----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
        LOG.info("Fetching & Deleting Customer with id {}", id);

        Customer customer = customerService.getCustomer(id);
        if ( null == customer ) {
            LOG.error("Unable to delete. Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        customer = customerService.deleteCustomer(customer);
        return  ResponseEntity.ok(customer);
    }
	*/
    
    private String formatMessage( BindingResult result)
    {
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        /*
        ErrorMessage errorMessageX = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        */
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode("01");
        errorMessage.setMessages(errors);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
