package at.videc.survia.node.security;

import at.videc.survia.node.domain.model.Role;
import at.videc.survia.node.domain.model.User;
import at.videc.survia.node.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("SurviaUserDetailsService")
public class SurviaUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public SurviaUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<? extends GrantedAuthority> grantedAuthorities = user.getRoles().stream().map(this::roleToAuthority).collect(Collectors.toList());

        return new SurviaUserDetails(grantedAuthorities, "", user.getUsername(), false, false, false, false);
    }

    private SimpleGrantedAuthority roleToAuthority(Role role) {
        return new SimpleGrantedAuthority(role.getName().name());
    }
}
