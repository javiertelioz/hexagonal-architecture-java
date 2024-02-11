package com.inicions.tasks.domain.ports.out;

import com.inicions.tasks.domain.model.AdditionalTaskInfo;

public interface ExternalServicePort {
    AdditionalTaskInfo getAdditionalTaskInfo(Long taskId);
}
