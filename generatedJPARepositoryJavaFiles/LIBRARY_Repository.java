package navy.ssp.ma.tfrUtility.dataImport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import navy.ssp.ma.tfrUtility.dataImport.entities.LIBRARY;

@Repository
public interface LIBRARY_Repository extends CrudRepository<LIBRARY, Long> {

}
