package guru.springframework.commands;


import java.math.BigDecimal;

/**
 * Created by jt on 1/10/17.
 */
public class EmployeeForm {
    private Long id;
    private String fullname ;
    private BigDecimal salary;
    private String departement;
    private String rank;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getDepartement() {
        return departement;
    }

    public String getRank() {
        return rank;
    }

    
}
