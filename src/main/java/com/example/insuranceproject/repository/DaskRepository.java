package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.model.Kasko;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaskRepository extends JpaRepository<Dask, Long> {
    Dask findDaskByContent(String content);

    List<Dask> findDasksByContentNot(String content);

    Dask findDaskById(Long id);

    @Override
    List<Dask> findAll();

    Dask save(Dask dask);
}
