/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm;

import cn.zhuatech.bpm.service.ApprovalDelegationService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApprovalDelegationServiceTests {
    private final ApprovalDelegationService service = new ApprovalDelegationService();

    @Test void activatesDelegationForBacklog() {
        var result = service.route(new ApprovalDelegationService.Request(30, 8, 24, 1, 2, false, 3));
        assertThat(result.status()).isEqualTo("ACTIVATE_DELEGATION");
        assertThat(result.delegatedAssignments()).isPositive();
    }

    @Test void escalatesSegregationConflict() {
        var result = service.route(new ApprovalDelegationService.Request(20, 26, 24, 1, 2, true, 5));
        assertThat(result.status()).isEqualTo("ESCALATE");
        assertThat(result.delegatedAssignments()).isZero();
    }
}
