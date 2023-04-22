import com.android.build.api.dsl.ApplicationExtension
import com.example.myapplication.configureDataBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidDataBindingPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
                with(pluginManager) {
                    // KAPT must go last to avoid build warnings.
                    // See: https://stackoverflow.com/questions/70550883/warning-the-following-options-were-not-recognized-by-any-processor-dagger-f
                    apply("org.jetbrains.kotlin.kapt")
                }
            val extension = extensions.getByType<ApplicationExtension>()
            configureDataBinding(extension)
        }
    }
}