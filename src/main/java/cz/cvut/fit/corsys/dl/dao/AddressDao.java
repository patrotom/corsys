package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface defines data layer interface for Adress management
 */

public interface AddressDao extends JpaRepository<Address, Integer> {

    /**
     * Find address by id.
     * @param id
     * @return Address
     */
    Address findAddressByAddressId(Integer id);

}
