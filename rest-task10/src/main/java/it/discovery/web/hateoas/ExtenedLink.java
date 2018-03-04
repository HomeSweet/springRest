package it.discovery.web.hateoas;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(Include.NON_NULL)
public class ExtenedLink extends Link{

}
