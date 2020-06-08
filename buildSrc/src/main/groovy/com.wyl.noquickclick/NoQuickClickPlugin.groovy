package com.wyl.noquickclick

import com.wyl.noquickclick.bean.NoQuickClickConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.AppExtension
class NoQuickClickPlugin implements Plugin<Project> {

    @Override
      void apply(Project project) {
        def android = project.extensions.findByType(AppExtension.class)
        project.getExtensions().add("noQuickClickConfig", NoQuickClickConfig)
        //注册Transform(
        android.registerTransform(new NoQuickClickTransform(project))
     
    }

}