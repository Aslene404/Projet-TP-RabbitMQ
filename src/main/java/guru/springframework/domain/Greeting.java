/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author USER
 */
@Entity
public class Greeting {
    @Id
    @GeneratedValue
     private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
  private String content;

  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

    
}
