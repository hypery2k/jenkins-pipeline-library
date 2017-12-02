import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class GitTest extends BasePipelineTest {

    @Before
    void prepare() throws Exception {
        setUp()
        helper.registerAllowedMethod("withEnv", [List, Closure], withEnvInterceptor)
        def withEnvInterceptor = { list, closure ->
                def env = binding.getVariable('env')

                for (String pair : list) {
                    int split = pair.indexOf('=')
                    if (split !=-1)
                        env.put(pair.substring(0, split), pair.substring(split +1))
                }
                binding.setVariable('env', env)
                def res = closure.call()
                return res
            }
    }

    @Test
    void should_work_with_isDevelopBranch() throws Exception {
        def git = loadScript("./test/end2end/de/mare/ci/jenkins/git/Jenkinsfile")
        git.isDevelopBranch()
        printCallStack()
        assertJobStatusSuccess()
    }
}
