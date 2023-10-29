package com.dogstore.dogstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.dogstore.dogstore.models.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {

}
