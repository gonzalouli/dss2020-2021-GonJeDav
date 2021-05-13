package com.jedago.practica_dss.admysql.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TransaccionRepository extends CrudRepository<Transaccion, String> {
	

}