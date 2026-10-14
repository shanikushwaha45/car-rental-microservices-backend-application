
package com.car_rental.customer_service.service;

import com.car_rental.customer_service.dto.CustomerLoginRequestDto;
import com.car_rental.customer_service.dto.CustomerRequestDto;
import com.car_rental.customer_service.dto.CustomerResponseDto;
import com.car_rental.customer_service.dto.LoginResponseDto;
import com.car_rental.customer_service.entity.Customer;
import com.car_rental.customer_service.exception.ResourceNotFoundException;
import com.car_rental.customer_service.mapper.CustomerMapper;
import com.car_rental.customer_service.repository.CustomerRepository;
import com.car_rental.customer_service.security.JwtService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private  final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;


    public CustomerResponseDto registerCustomer(CustomerRequestDto requestDto) {

        if (customerRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        Customer customer = customerMapper.toEntity(requestDto);

        // Hash the password before saving
        customer.setPassword(
                passwordEncoder.encode(requestDto.getPassword())
        );

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDto(savedCustomer);
    }

    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }


    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id: " + id
                        ));

        return customerMapper.toResponseDto(customer);
    }

    public CustomerResponseDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with email: " + email
                        ));

        return customerMapper.toResponseDto(customer);
    }



    public LoginResponseDto loginCustomer(
            CustomerLoginRequestDto requestDto) {

        Customer customer = customerRepository
                .findByEmail(requestDto.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(
                requestDto.getPassword(),
                customer.getPassword()
        );

        if (!passwordMatches) {
            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        String token = jwtService.generateToken(customer.getEmail());

        CustomerResponseDto customerResponse =
                customerMapper.toResponseDto(customer);

        return new LoginResponseDto(
                token,
                "Bearer",
                customerResponse
        );
    }
}