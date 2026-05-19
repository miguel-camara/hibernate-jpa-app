package com.miguel.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import com.miguel.hibernate.entity.Cliente;
import com.miguel.hibernate.util.JpaUtil;

import java.util.Scanner;

public class HibernateSingleResultWhere {
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);

    EntityManager em = JpaUtil.getEntityManager();
    Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
    System.out.println("Ingrese una forma de pago: ");
    String pago = s.next();
    query.setParameter(1, pago);
    query.setMaxResults(1);
    try {
      Cliente c = (Cliente) query.getSingleResult();
      System.out.println(c);

    } catch (Exception e) {
      System.out.println("No hay resultado");

    } finally {

      em.close();
      s.close();
    }
  }
}
