package yte.app.application.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}