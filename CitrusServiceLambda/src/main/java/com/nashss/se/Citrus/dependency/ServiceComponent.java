package com.nashss.se.Citrus.dependency;

import com.nashss.se.Citrus.activity.GetUserActivity;
import dagger.Component;
import javax.inject.*;

@Singleton
@Component(modules= {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetUserActivity provideGetUserActivity();
}