package uit.thesis.assessment_mgnt.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.security.dto.UserDetailsDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username is invalid");
        Set<GrantedAuthority> authorities = getAuthorities(user.getRoles());
        return new UserDetailsDto(user.getUsername(), user.getPassword(), authorities);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        Iterator<Role> iterator = roles.iterator();

        while(iterator.hasNext())
            authorities.add(new SimpleGrantedAuthority(iterator.next().getName()));

        return authorities;
    }
}
