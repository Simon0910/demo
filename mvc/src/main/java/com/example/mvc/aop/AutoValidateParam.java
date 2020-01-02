package com.example.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 自动验证controller层的参数
 *
 * @author liuzhipeng
 * @description
 * @create 2019/10/29 9:25
 */
@Slf4j
@Component
@Aspect
@Order(2)
public class AutoValidateParam {

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.example.mvc.controller.IndexController.*.*(..))")
    public void cutBindingResultService() {}


    /**
     * 在切入点开始处切入内容
     *
     * @param joinPoint
     */
    @Around("cutBindingResultService()")
    public Object around(ProceedingJoinPoint joinPoint) {
        ValidateResult validate = new ValidateResult();
        Object result = null;

        // 获取所有的请求参数
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            // log
            Signature signature = joinPoint.getSignature();
            log.info("{}.{}() 第一个请求参数: {}", signature.getDeclaringTypeName(), signature.getName(), args[0]);
            for (Object obj : args) {
                if (obj instanceof BindingResult) {
                    // 参数验证
                    validate = validate((BindingResult) obj);
                    break;
                }
            }
        }
        // 验证通过执行拦截方法，否则不执行
        if (validate.isValidatePass()) {
            try {
                // 执行拦截方法
                result = joinPoint.proceed();
            } catch (Throwable ex) {
                log.error("AOP执行拦截方法时异常, {}", ex);
                // result = Result.createByError(ErrCodeEnum.SYS_ERR);
            }
        } else {
            log.error("AOP执行拦截方法时异常, {}:{}", validate.getField(), validate.getErrorMessage());
            // result = Result.createByError(ErrCodeEnum.PARAM_ERR);
        }
        return result;
    }

    private ValidateResult validate(BindingResult bindingResult) {
        ValidateResult validateResult = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            validateResult = new ValidateResult(Boolean.FALSE, fieldError.getField(), fieldError.getDefaultMessage());
        } else {
            validateResult = new ValidateResult(Boolean.TRUE, "参数合法性校验通过！");
        }
        // 参数验证结果
        // log.info("请求参数验证结果：{}", validateResult);
        return validateResult;
    }

}
