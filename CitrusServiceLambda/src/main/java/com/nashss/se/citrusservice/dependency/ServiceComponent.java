package com.nashss.se.citrusservice.dependency;

import dagger.Component;
import javax.inject.*;

import com.nashss.se.citrusservice.activity.GetUserActivity;

@Singleton
@Component(modules= {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetUserActivity provideGetUserActivity();
}