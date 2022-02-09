package com.mutualmobile.feat.githubrepos.di

import com.mutualmobile.feat.githubrepos.ui.model.UIRepoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [GithubReposVMModule::class]

)
class GithubReposVMModuleTest {

    @Provides
    fun provideUiModelMapper(): UIRepoMapper {
        return UIRepoMapper()
    }
}

