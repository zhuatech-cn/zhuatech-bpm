/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.repository;
import cn.zhuatech.bpm.model.ProcessDefinition;import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;
public interface ProcessDefinitionRepository extends JpaRepository<ProcessDefinition,Long>{Optional<ProcessDefinition> findByAccountCode(String accountCode);}
