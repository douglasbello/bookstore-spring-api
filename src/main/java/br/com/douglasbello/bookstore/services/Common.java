package br.com.douglasbello.bookstore.services;

import java.util.List;

/* this interface represents common methods that all services in this project will use. */
public interface Common<T> {
    public T findById(Integer id);
    public List<T> findAll();
    public T save(T obj);
    public T update(Integer old, T _new);
    public void delete(Integer obj);
}