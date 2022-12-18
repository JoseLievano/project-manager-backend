package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class bsGeneralSettingsPredicate extends CommonPathExpression<bsGeneralSettingsEntity> {

    private final QbsGeneralSettingsEntity bsGeneralSettingsEntity = QbsGeneralSettingsEntity.bsGeneralSettingsEntity;

    public bsGeneralSettingsPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsGeneralSettingsEntity>(bsGeneralSettingsEntity.class, "bsGeneralSettingsEntity");
    }

}
