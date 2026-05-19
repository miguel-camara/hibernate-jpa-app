package com.miguel.hibernate;

import jakarta.persistence.EntityManager;
import com.miguel.hibernate.entity.Cliente;
import com.miguel.hibernate.services.ClienteService;
import com.miguel.hibernate.services.ClienteServiceImpl;
import com.miguel.hibernate.util.JpaUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HibernateCrudService {
  private static EntityManager em;

  private static ClienteService service;

  private static List<Cliente> listClientes;

  private static Scanner scanner;

  public static void main(String[] args) {

    em = JpaUtil.getEntityManager();
    service = new ClienteServiceImpl(em);
    listClientes = Arrays.asList(
        new Cliente(null, "Miguel", "Ordoñez", "Mercado Pago"),
        new Cliente(null, "Antonio", "Camara", "Debito"),
        new Cliente(null, "Juan", "Varguez", "Credito"),
        new Cliente(null, "Maria", "Camara", "Prestamo"),
        new Cliente(null, "Mariana", "Ordoñez", "Efectivo"),
        new Cliente(null, "Carmen", "Casanova", "PayPal"),
        new Cliente(null, "Andres", "Chi", "Mercado Pago"));

    scanner = new Scanner(System.in);

    boolean estaActivo = true;
    int op = 0;
    do {

      try {
        System.out.println("1. Por ID");
        System.out.println("2. Listar");
        System.out.println("3. Eliminar");
        System.out.println("4. Editar");
        System.out.println("5. Agregar");
        System.out.println("6. Salir");
        op = scanner.nextInt();

        switch (op) {
          case 1:
            byId();
            break;
          case 2:
            list();
            break;
          case 3:
            delete();
            break;
          case 4:
            edit();
            break;
          case 5:
            add();
            break;

          default:
            estaActivo = false;
        }

      } catch (Exception e) {
        System.err.println("Ocurrio un error");
        e.printStackTrace();
        return;
      }
    } while (estaActivo);

    em.close();
    scanner.close();
  }

  public static void add() {
    System.out.println("========== Agregar ===========");
    Cliente cliente = listClientes.get(getIndex());
    service.guardar(cliente);
    System.out.println("cliente guardado con exito\n");
  }

  public static void edit() {
    System.out.println("=========== Editar ==========");
    Long id = 0L;
    System.out.print("Ingrese el Id a Editar: ");
    id = scanner.nextLong();
    Optional<Cliente> optionalCliente = service.porId(id);
    optionalCliente.ifPresentOrElse(c -> {
      c.setFormaPago(listClientes.get(getIndex()).getFormaPago() + "-" + String.valueOf(getIndex()));
      service.guardar(c);
      System.out.println("Cliente editado con exito!\n");
    }, () -> System.out.println("No se encontro el id\n"));
  }

  public static void delete() {
    System.out.println("========== Eliminar ===========");
    Long id = 0L;
    System.out.print("Ingrese el Id a Eliminar: ");
    id = scanner.nextLong();
    Optional<Cliente> optionalCliente = service.porId(id);
    optionalCliente.ifPresentOrElse(c -> {
      service.eliminar(c.getId());
      System.out.println("Cliente eliminado con exito!\n");
    }, () -> System.out.println("No se encontro el id\n"));
  }

  public static void list() {
    System.out.println("========== Listar ==========");
    List<Cliente> clientes = service.listar();
    clientes.forEach(System.out::println);
    System.out.println("");
  }

  public static void byId() {
    System.out.println("========== PorID ==========");
    Long id = 0L;
    System.out.print("Ingrese el Id a buscar: ");
    id = scanner.nextLong();
    Optional<Cliente> optionalCliente = service.porId(id);
    optionalCliente.ifPresentOrElse(System.out::println, () -> System.out.println("No se encontro el id"));
    System.out.println("");
  }

  public static int getIndex() {
    return (int) Math.floor(Math.random() * listClientes.size());
  }
}
