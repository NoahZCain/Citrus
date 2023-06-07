package com.nashss.se.citrusservice.dependency;


import com.nashss.se.citrusservice.activity.UpdateUserActivity;

import com.nashss.se.citrusservice.activity.GetPlaceActivity;

import dagger.Component;
import javax.inject.*;

import com.nashss.se.citrusservice.activity.GetUserActivity;

@Singleton
@Component(modules= {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetUserActivity provideGetUserActivity();
    UpdateUserActivity provideUpdateUserActivity();

    GetPlaceActivity provideGetPlaceActivity();
}