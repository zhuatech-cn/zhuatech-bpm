/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.repository;
import cn.zhuatech.bpm.model.ProcessTask;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface ProcessTaskRepository extends JpaRepository<ProcessTask,Long>{List<ProcessTask> findAllByOrderByDueDateAsc();}
