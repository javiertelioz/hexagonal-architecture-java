package com.inicions.tasks.application.usecases;

import com.inicions.tasks.domain.model.AdditionalTaskInfo;
import com.inicions.tasks.domain.ports.in.GetAdditionalTaskInfoUseCase;
import com.inicions.tasks.domain.ports.out.ExternalServicePort;

public class GetAdditionalTaskInfoUseCaseImpl implements GetAdditionalTaskInfoUseCase {

    private final ExternalServicePort externalServicePort;

    public GetAdditionalTaskInfoUseCaseImpl(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long taskId) {
        return externalServicePort.getAdditionalTaskInfo(taskId);
    }
}
