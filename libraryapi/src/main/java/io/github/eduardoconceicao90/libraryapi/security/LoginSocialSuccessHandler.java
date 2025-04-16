package io.github.eduardoconceicao90.libraryapi.security;

import io.github.eduardoconceicao90.libraryapi.model.Usuario;
import io.github.eduardoconceicao90.libraryapi.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String SENHA_PADRAO = "123456";

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oAuth2Token.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        Usuario usuario = usuarioService.obterPorEmail(email);

        if(usuario == null) {
            usuario = cadastrarUsuario(email);
        }

        authentication = new CustomAuthentication(usuario);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private Usuario cadastrarUsuario(String email) {
        Usuario usuario = new Usuario();
        usuario.setLogin(obterLogin(email));
        usuario.setEmail(email);
        usuario.setSenha(SENHA_PADRAO);
        usuario.setRoles(List.of("OPERADOR"));

        usuarioService.salvar(usuario);
        return usuario;
    }

    private String obterLogin(String email) {
        return email.substring(0, email.indexOf("@"));
    }

}
