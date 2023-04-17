import com.android.build.api.dsl.ApplicationExtension
import com.example.myapplication.configureDataBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidDataBindingPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<ApplicationExtension>()
            configureDataBinding(extension)
        }
    }
}