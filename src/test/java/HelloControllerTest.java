import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.bedkowski.spring.mvc.lambda.Application;
import pl.bedkowski.spring.mvc.lambda.aws.StreamLambdaHandler;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration
public class HelloControllerTest {
    private MockLambdaContext lambdaContext;
    private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    
    @Autowired
    private ObjectMapper mapper;
 
    public HelloControllerTest() { 
       lambdaContext = new MockLambdaContext(); 
       this.handler = StreamLambdaHandler.handler;
    }

    @Test
    public void testApi() throws Exception {
       AwsProxyRequest request = new AwsProxyRequestBuilder("/", "GET").build();
       AwsProxyResponse response = handler.proxy(request, lambdaContext);
      
       assertThat(response.getStatusCode(), equalTo(200));
       assertThat(response.getBody(), equalTo("{\"success\":true,\"message\":\"Hello World\"}"));
//       GreetingDto responseBody = mapper.readValue(response.getBody(), GreetingDto.class);
//       asserThat(responseBody.getMessage(), equalTo("Hello John"));
    }
}