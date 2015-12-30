/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "autor")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id")
    private int id;
    @Column(name = "nome")
    private String nome;

    public Autor() {
    }

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor(String nome) {
        this.nome = nome;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
