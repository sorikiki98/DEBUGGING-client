package com.example.application.di;

import com.example.application.DebuggingApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivitiesModule.class, NetworkModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(DebuggingApplication application);

        AppComponent build();
    }

    void inject(DebuggingApplication app);
}
