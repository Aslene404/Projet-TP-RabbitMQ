package guru.springframework.repositories;

import guru.springframework.domain.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100.00);
    private static final String PRODUCT_DESCRIPTION = "a cool employee";
    private static final String IMAGE_URL = "departement 1";
    private static final String IMAGE_URL1 = "rank 1";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Employee employee = new Employee();
        employee.setFullname(PRODUCT_DESCRIPTION);
        employee.setDepartement(IMAGE_URL);
        employee.setRank(IMAGE_URL1);
        employee.setSalary(BIG_DECIMAL_100);

        //when
        employeeRepository.save(employee);

        //then
        Assert.assertNotNull(employee.getId());
        Employee newEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        Assert.assertEquals((Long) 1L, newEmployee.getId());
        Assert.assertEquals(PRODUCT_DESCRIPTION, newEmployee.getFullname());
        Assert.assertEquals(BIG_DECIMAL_100.compareTo(newEmployee.getSalary()), 0);
        Assert.assertEquals(IMAGE_URL, newEmployee.getDepartement());
        Assert.assertEquals(IMAGE_URL1, newEmployee.getRank());
    }
}