package com.nashss.se.citrusservice.dependency;


import com.nashss.se.citrusservice.activity.*;

import dagger.Component;
import javax.inject.*;

@Singleton
@Component(modules= {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetUserActivity provideGetUserActivity();
    UpdateUserActivity provideUpdateUserActivity();
    GetPlaceActivity provideGetPlaceActivity();
    CreateUserActivity provideCreateUserActivity();
    UpdateInterestsActivity provideUpdateInterestsActivity();
    AddAccessibilityTagsActivity provideAddAccessibilityTagsActivity();
    SearchForPlaceActivity provideSearchForPlaceActivity();
    RemoveAccessibilityTagsActivity provideRemoveTagsActivity();
}