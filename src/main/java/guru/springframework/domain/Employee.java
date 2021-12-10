package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by jt on 1/10/17.
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue
     private Long id;
    private String fullname ;
    private BigDecimal salary;
    private String departement;
    private String rank;
    private boolean messageReceived;
    private Integer messageCount = 0; //init to zero
    private String msg_recu;

    public String getMsg_recu() {
        return msg_recu;
    }

    public void setMsg_recu(String msg_recu) {
        this.msg_recu = msg_recu;
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

    

    public boolean isMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(boolean messageReceived) {
        this.messageReceived = messageReceived;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}
