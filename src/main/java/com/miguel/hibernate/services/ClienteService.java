package com.miguel.hibernate.services;

import java.util.List;
import java.util.Optional;

import com.miguel.hibernate.entity.Cliente;

public interface ClienteService {
  List<Cliente> listar();

  Optional<Cliente> porId(Long id);

  void guardar(Cliente cliente);

  void eliminar(Long id);
}
