
package br.com.maquiagemimportada.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(
						"/css/**",
						"/fonts/**",
						"/images/**",
						"/js/**",
						"/vendors/**",
						"/index",
						"/vitrine",
						"/vitrine/**",
						"/"
				).permitAll()
				.antMatchers(
						"/perfil",
						"/perfil/**",
						"/pagamento",
						"/pagamento/**"
				).hasRole("USER")
				.antMatchers(
						"/produto",
						"/produto/**",
						"/categoria",
						"/categoria/**",
						"/dashboard",
						"/pedido",
						"/pedido/**",
						"/usuario",
						"/usuario/**",
						"/configuracao",
						"/configuracao/**"
				).hasRole("ADMIN")
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER");
//	}
	
	@SuppressWarnings("deprecation")
	@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
