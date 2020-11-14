package edu.ucmo.spring_example.service;

import edu.ucmo.spring_example.dao.EmployeeDao;
import edu.ucmo.spring_example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeDao EmployeeDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Employee employee = EmployeeDao.findByEmail(email);
        if(employee == null) {
            throw new UsernameNotFoundException(String.format("The email %s doesn't exist", email));
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("User"));
        return buildUserForAuthentication(employee, authorities);
    }


    private UserDetails buildUserForAuthentication(Employee employee, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassHash(),
                employee.getActive(), true, true, true, authorities);
    }
}