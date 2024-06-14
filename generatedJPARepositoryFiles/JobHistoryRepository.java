package navy.ssp.ma.tfrUtility.dataImport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.JobHistory;

@Repository
public interface JobHistoryRepository extends CrudRepository<JobHistory, Long>{

}
