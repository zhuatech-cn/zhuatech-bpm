/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.repository;
import cn.zhuatech.bpm.model.ProcessInstance;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;import java.util.Optional;
public interface ProcessInstanceRepository extends JpaRepository<ProcessInstance,Long>{Optional<ProcessInstance> findByProcessInstanceNo(String no);List<ProcessInstance> findAllByOrderByDueDateAsc();}
