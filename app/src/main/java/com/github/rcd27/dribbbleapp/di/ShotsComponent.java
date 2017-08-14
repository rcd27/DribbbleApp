package com.github.rcd27.dribbbleapp.di;


import com.github.rcd27.dribbbleapp.shots.view.ShotsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = ShotsModule.class)
public interface ShotsComponent {

    void inject(ShotsFragment shotsFragment);

}
