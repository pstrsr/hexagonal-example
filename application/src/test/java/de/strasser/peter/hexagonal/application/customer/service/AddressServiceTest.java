package de.strasser.peter.hexagonal.application.customer.service;

import de.strasser.peter.hexagonal.application.customer.mapper.AddAddressMapper;
import de.strasser.peter.hexagonal.application.customer.port.out.AddressValidatorAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AddressServiceTest {

    @Autowired
    private AddressService sut;
    @MockBean
    private SaveCustomerAdapter saveCustomerAdapterMock;
    @MockBean
    private AddressValidatorAdapter addressValidatorAdapterMock;
    @MockBean
    private LoadCustomerAdapter loadCustomerAdapterMock;
    @MockBean
    private AddAddressMapper addAddressMapperMock;


}
