package com.example.application.di;

import com.example.application.DebuggingApplication;
import com.example.application.SchedulersFacade;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, StoragesModule.class, ActivitiesModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(DebuggingApplication application);
        Builder storagesModule(StoragesModule module);
        AppComponent build();
    }

    void inject(DebuggingApplication app);
    void inject(SchedulersFacade schedulersFacade);
}
