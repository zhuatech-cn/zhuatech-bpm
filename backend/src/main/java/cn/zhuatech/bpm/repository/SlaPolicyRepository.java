/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.repository;
import cn.zhuatech.bpm.model.SlaPolicy;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface SlaPolicyRepository extends JpaRepository<SlaPolicy,Long>{List<SlaPolicy> findByFiscalYearOrderByDepartmentAsc(int fiscalYear);}
