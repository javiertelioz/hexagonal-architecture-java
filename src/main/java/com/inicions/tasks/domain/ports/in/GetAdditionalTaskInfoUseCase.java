package com.inicions.tasks.domain.ports.in;

import com.inicions.tasks.domain.model.AdditionalTaskInfo;

public interface GetAdditionalTaskInfoUseCase {
    AdditionalTaskInfo getAdditionalTaskInfo(Long taskId);
}
