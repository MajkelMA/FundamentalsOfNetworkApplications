package com.lapciakbilicki.pas2.validator;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.List;

@FacesValidator("sportsFacilityUniqueNameValidator")
public class SportsFacilityUniqueNameValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        List<SportsFacility> sportsFacilities = (List<SportsFacility>)uiComponent.getAttributes().get("allFacility");
        String name = (String)o;
        SportsFacility findSportsFacility = sportsFacilities.stream()
                .filter(sportsFacility -> sportsFacility.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(findSportsFacility != null){
            throw new ValidatorException(new FacesMessage("None unique name"));
        }
    }
}
