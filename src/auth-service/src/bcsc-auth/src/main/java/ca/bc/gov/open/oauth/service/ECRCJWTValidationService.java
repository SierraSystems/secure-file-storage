package ca.bc.gov.open.oauth.service;

import ca.bc.gov.open.oauth.model.ValidationResponse;

/**
 * 
 * BCSC JWT Token Validation Services.  
 * 
 * @author shaunmillargov
 *
 */
public interface ECRCJWTValidationService {
   public abstract ValidationResponse validateBCSCAccessToken(String token);
   public abstract ValidationResponse validateBCSCIDToken(String token);
}
