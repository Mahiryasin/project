package com.mahir.AuditAware;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.mapstruct.Qualifier;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component(value ="AuditAware")
public class AuditAwareimpl implements  AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
      return Optional.of("Admin");// normalde securityde 
    }

}
