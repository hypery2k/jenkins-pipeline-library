import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test
import org.codehaus.groovy.runtime.metaclass.MethodSelectionException

class NodeJsTest extends BasePipelineTest {

    @Before
    void prepare() throws Exception {
        setUp()
    }

    @Test
    void should_work_with_npm_install() throws Exception {
        def nodeJS = loadScript("./test/end2end/de/mare/ci/jenkins/nodejs/Jenkinsfile")
        nodeJS.nvm('install')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_handle_npm_run_target() throws Exception {
        def nodeJS = loadScript("./test/end2end/de/mare/ci/jenkins/nodejs/Jenkinsfile")
        nodeJS.nvmRun('test2')
        printCallStack()
        assertJobStatusSuccess()
    }

}
