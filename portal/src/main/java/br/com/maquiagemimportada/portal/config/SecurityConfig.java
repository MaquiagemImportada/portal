
package br.com.maquiagemimportada.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.maquiagemimportada.portal.security.MIUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = MIUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
		.antMatchers(
				"/css/**",
				"/fonts/**",
				"/images/**",
				"/js/**",
				"/vendors/**",
				"/index",
				"/vitrine",
				"/vitrine/**",
				"/cadastro/**",
				"/"
		);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/usuario").hasAuthority("USUARIO_VISUALIZAR")
				.antMatchers("/usuario/novo/**").hasAuthority("USUARIO_CRIAR")
				.antMatchers("/usuario/editar/**").hasAuthority("USUARIO_EDITAR")
				.antMatchers("/usuario/apagar/**").hasAuthority("USUARIO_APAGAR")
				.antMatchers("/perfil").hasAuthority("PERFIL_VISUALIZAR")
				.antMatchers("/perfil/novo/**").hasAuthority("PERFIL_CRIAR")
				.antMatchers("/perfil/editar/**").hasAuthority("PERFIL_EDITAR")
				.antMatchers("/perfil/apagar/**").hasAuthority("PERFIL_APAGAR")
				.antMatchers("/permissao").hasAuthority("PERMISSAO_VISUALIZAR")
				.antMatchers("/permissao/novo/**").hasAuthority("PERMISSAO_CRIAR")
				.antMatchers("/permissao/editar/**").hasAuthority("PERMISSAO_EDITAR")
				.antMatchers("/permissao/apagar/**").hasAuthority("PERMISSAO_APAGAR")
				.antMatchers("/produto").hasAuthority("PRODUTO_VISUALIZAR")
				.antMatchers("/produto/novo/**").hasAuthority("PRODUTO_CRIAR")
				.antMatchers("/produto/editar/**").hasAuthority("PRODUTO_EDITAR")
				.antMatchers("/produto/apagar/**").hasAuthority("PRODUTO_APAGAR")
				.antMatchers("/categoria").hasAuthority("CATEGORIA_VISUALIZAR")
				.antMatchers("/categoria/novo/**").hasAuthority("CATEGORIA_CRIAR")
				.antMatchers("/categoria/editar/**").hasAuthority("CATEGORIA_EDITAR")
				.antMatchers("/categoria/apagar/**").hasAuthority("CATEGORIA_APAGAR")
				.antMatchers("/configuracao").hasAuthority("CONFIGURACAO_VISUALIZAR")
				.antMatchers("/configuracao/novo/**").hasAuthority("CONFIGURACAO_CRIAR")
				.antMatchers("/configuracao/editar/**").hasAuthority("CONFIGURACAO_EDITAR")
				.antMatchers("/configuracao/apagar/**").hasAuthority("CONFIGURACAO_APAGAR")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
