package com.miguel.hibernate.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Embeddable
public class Auditoria {

  @Column(name = "creado_en")
  private LocalDateTime creadoEn;

  @Column(name = "editado_en")
  private LocalDateTime editadoEn;

  @PrePersist
  public void prePersist() {
    System.out.println("inicializar algo justo antes del persist");
    this.creadoEn = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    System.out.println("inicializar algo justo antes del update");
    this.editadoEn = LocalDateTime.now();
  }

  public LocalDateTime getCreadoEn() {
    // if (creadoEn == null)
    // return LocalDateTime.now();
    return creadoEn;
  }

  public void setCreadoEn(LocalDateTime creadoEn) {
    this.creadoEn = creadoEn;
  }

  public LocalDateTime getEditadoEn() {
    // if (editadoEn == null)
    // return LocalDateTime.now();
    return editadoEn;
  }

  public void setEditadoEn(LocalDateTime editadoEn) {
    this.editadoEn = editadoEn;
  }

  @Override
  public String toString() {
    return "Auditoria [creadoEn=" + creadoEn + ", editadoEn=" + editadoEn + "]";
  }

}
