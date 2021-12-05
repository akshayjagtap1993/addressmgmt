package com.person.addressmgmt.repository;

import com.person.addressmgmt.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}

/*@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}*/
