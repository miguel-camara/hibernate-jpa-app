package com.miguel.hibernate.services;

import java.util.List;
import java.util.Optional;

import com.miguel.hibernate.entity.Cliente;
import com.miguel.hibernate.repositories.ClienteRepository;
import com.miguel.hibernate.repositories.CrudRepository;

import jakarta.persistence.EntityManager;

public class ClienteServiceImpl implements ClienteService {
  private EntityManager em;
  private CrudRepository<Cliente> repository;

  public ClienteServiceImpl(EntityManager em) {
    this.em = em;
    this.repository = new ClienteRepository(em);
  }

  @Override
  public List<Cliente> listar() {
    return repository.listar();
  }

  @Override
  public Optional<Cliente> porId(Long id) {
    return Optional.ofNullable(repository.porId(id));
  }

  @Override
  public void guardar(Cliente cliente) {

    try {
      em.getTransaction().begin();
      repository.guardar(cliente);
      em.getTransaction().commit();
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
    }
  }

  @Override
  public void eliminar(Long id) {
    try {
      em.getTransaction().begin();
      repository.eliminar(id);
      em.getTransaction().commit();
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
    }
  }
}
