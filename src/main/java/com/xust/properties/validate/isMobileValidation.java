package com.xust.properties.validate;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xust.loginAndregister.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author: Victor
 * @create: 2019-08-15 11:04
 **/

public class isMobileValidation implements ConstraintValidator<isMobile,String> {

    private boolean required = false;
    @Override
    public void initialize(isMobile constraintAnnotation) {
       required =  constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return ValidatorUtil.isMobile(value);
        }else {
            if(StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }

        }
    }
}
