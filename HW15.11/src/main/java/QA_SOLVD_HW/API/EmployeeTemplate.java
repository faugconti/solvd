package QA_SOLVD_HW.API;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.annotation.method.DeleteMethod;
import com.zebrunner.carina.api.annotation.method.PutMethod;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Header(key = "Cookie", value = "humans_21909=1")
@EndpointTemplate(url = "${config.env.api_url}")
public interface EmployeeTemplate {

    @EndpointTemplateMethod(url = "/create", methodType = HttpMethodType.POST)
    AbstractApiMethodV2 create(@RequestTemplatePath.Value String rqPath, @ResponseTemplatePath.Value String rsPath, @PropertiesPath.Value String propsPath);

    @EndpointTemplateMethod(url = "/employees", methodType = HttpMethodType.GET)
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    @ResponseTemplatePath(path = "api/employees/_get/rs.json")
    @PropertiesPath(path = "api/employees/employee.properties")
    AbstractApiMethodV2 getAll();

    @EndpointTemplateMethod(url = "/employee/${id}", methodType = HttpMethodType.GET)
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    @ResponseTemplatePath(path = "api/employees/_getById/rs.json")
    @PropertiesPath(path = "api/employees/employee.properties")
    AbstractApiMethodV2 getById(@PathParam(key = "id") Long id);

    @DeleteMethod(url = "/delete/${id}")
    @ResponseTemplatePath(path = "api/employees/_delete/rs.json")
    AbstractApiMethodV2 deleteById(@PathParam(key = "id") Long id);

    @PutMethod(url = "/update/${id}")
    @PropertiesPath(path = "api/employees/employee.properties")
    @ResponseTemplatePath(path = "api/employees/_put/rs.json")
    @RequestTemplatePath(path= "api/employees/_put/rq.json")
    AbstractApiMethodV2 updateById(@PathParam(key = "id") Long id);
}
