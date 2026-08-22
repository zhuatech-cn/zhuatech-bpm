/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm;

import cn.zhuatech.bpm.service.ProcessSlaSimulationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessSlaSimulationServiceTests {
    private final ProcessSlaSimulationService service = new ProcessSlaSimulationService();

    @Test
    void recommendsMoreParallelWorkersForSlaMiss() {
        var result = service.simulate(new ProcessSlaSimulationService.Request(
            "PROC-PURCHASE", 3, 8, 60, 2, 180, .25));

        assertEquals(300, result.projectedMinutes());
        assertEquals("SLA_MISS", result.decision());
        assertEquals(4, result.requiredParallelWorkers());
        assertTrue(result.actions().getFirst().contains("4"));
    }

    @Test
    void keepsHealthyProcessOnTrack() {
        var result = service.simulate(new ProcessSlaSimulationService.Request(
            "PROC-LEAVE", 2, 3, 20, 2, 120, .05));

        assertEquals("ON_TRACK", result.decision());
    }
}
