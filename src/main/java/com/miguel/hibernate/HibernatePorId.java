package com.miguel.hibernate;

import jakarta.persistence.EntityManager;
// import jakarta.persistence.Query;
import com.miguel.hibernate.entity.Cliente;
import com.miguel.hibernate.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);

    System.out.println("Ingrese el id: ");
    Long id = s.nextLong();
    EntityManager em = JpaUtil.getEntityManager();
    Cliente cliente = em.find(Cliente.class, id);
    if (cliente != null) {
      System.out.println(cliente);
    } else {
      System.out.println("No hay resultados");
    }

    Cliente cliente2 = em.find(Cliente.class, id);
    if (cliente2 != null) {
      System.out.println(cliente2);
    } else {
      System.out.println("No hay resultados");
    }

    s.close();
    em.close();
  }
}
