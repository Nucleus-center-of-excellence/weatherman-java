package com.org.project.twm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



/**
 * This service is used to prepare mail content.
 * 
 * @author kapil.sharma
 * @since 08-july-2019
 */
@Service
public class MailContentBuilder {
	
	private static final String NEW_USER_EMAIL_TEMPLATE = "NewUserEmailTemplate";
  /** The template engine. */
  @Autowired
  private TemplateEngine templateEngine;

  /**
   * Builds the html Mail Content.
   *
   * @param emailTemplate the email template
   * @param activationLink the activation link
   * @return the string
   */
  @Autowired
  public MailContentBuilder(TemplateEngine templateEngine) {
      this.templateEngine = templateEngine;
  }
 
  
  public String build( String activationLink) {
    Context context = new Context();
    context.setVariable("activationLink", activationLink);
    return templateEngine.process(NEW_USER_EMAIL_TEMPLATE, context);
  }

}
