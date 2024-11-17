package API;

import com.zebrunner.carina.api.APIMethodPoller;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.api.binding.TemplateFactory;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import QA_SOLVD_HW.API.EmployeeTemplate;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class APItest implements IAbstractTest {

    @Test()
    @MethodOwner(owner = "Favio")
    public void test_GET_EMPLOYEES() {
        EmployeeTemplate employeeTemplate = TemplateFactory.prepareTemplate(EmployeeTemplate.class);
        AbstractApiMethodV2 api = employeeTemplate.getAll();
        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> rs.getStatusCode() == 200)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(600, ChronoUnit.SECONDS)
                .execute();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponseAgainstSchema("api/employees/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "Favio")
    public void test_GET_EMPLOYEE() {
        EmployeeTemplate employeeTemplate = TemplateFactory.prepareTemplate(EmployeeTemplate.class);
        AbstractApiMethodV2 api = employeeTemplate.getById(2L);
        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> rs.getStatusCode() == 200)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(600, ChronoUnit.SECONDS)
                .execute();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/employees/_getById/rs.schema");
    }

    @Test()
    @MethodOwner(owner="Favio")
    public void test_CREATE_EMPLOYEE(){
        String basedir = "api/employees";
        EmployeeTemplate employeeTemplate = TemplateFactory.prepareTemplate(EmployeeTemplate.class);
        AbstractApiMethodV2 api = employeeTemplate.create(
                    basedir+"/_post/rq.json",
                    basedir+"/_post/rs.json",
                basedir+"/employee.properties");

        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> rs.getStatusCode() == 200)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(600, ChronoUnit.SECONDS)
                .execute();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test()
    @MethodOwner(owner="Favio")
    public void test_PUT_EMPLOYEE(){
        EmployeeTemplate employeeTemplate = TemplateFactory.prepareTemplate(EmployeeTemplate.class);
        AbstractApiMethodV2 api = employeeTemplate.updateById(2L);
        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> rs.getStatusCode() == 200)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(600, ChronoUnit.SECONDS)
                .execute();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test()
    @MethodOwner(owner="Favio")
    public void test_DELETE_EMPLOYEE() {
        EmployeeTemplate employeeTemplate = TemplateFactory.prepareTemplate(EmployeeTemplate.class);
        AbstractApiMethodV2 api = employeeTemplate.deleteById(2L);

        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> rs.getStatusCode() == 200)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(600, ChronoUnit.SECONDS)
                .execute();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }
}
