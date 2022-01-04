package com.wsw.patrickstar.recepienter.repository;

import com.wsw.patrickstar.recepienter.entity.Recepienter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author WangSongWen
 * @Date: Created in 15:32 2021/1/19
 * @Description:
 */
@Repository
public interface RecepienterRepository extends JpaRepository<Recepienter, Long> {
//    @Modifying
//    @Transactional
//    @Query(value = "insert into recepienter (task_id, task_name, name, remark) values (?1, ?2, ?3, ?4)", nativeQuery = true)
//    int save(Long taskId, String taskName, String name, String remark);

}
