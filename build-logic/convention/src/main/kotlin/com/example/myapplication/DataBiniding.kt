package com.example.myapplication

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureDataBinding(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            viewBinding = true
        }
    }
}