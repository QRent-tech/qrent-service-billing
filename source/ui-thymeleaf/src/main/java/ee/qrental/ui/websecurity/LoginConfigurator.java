package ee.qrental.ui.websecurity;

import ee.qrental.ui.GitInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfigurator  implements WebMvcConfigurer {

    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(new GitInfoInterceptor());
        registry.addInterceptor(new TransactionInterceptor()).addPathPatterns("/person/save/*");
    }

}
