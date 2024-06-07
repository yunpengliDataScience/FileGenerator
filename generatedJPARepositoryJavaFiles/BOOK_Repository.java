package navy.ssp.ma.tfrUtility.dataImport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import navy.ssp.ma.tfrUtility.dataImport.entities.BOOK;

@Repository
public interface BOOK_Repository extends CrudRepository<BOOK, Long> {

}
