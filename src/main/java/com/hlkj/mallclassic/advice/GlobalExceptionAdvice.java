package com.hlkj.mallclassic.advice;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.configration.ExceptionCodeConfiguration;
import com.hlkj.mallclassic.exception.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xiang-ping li
 * @descition 用这个类接收从代码里抛出的异常，组织成UnifyResponse统一响应
 * @date 2020/5/15 0015  10:31
 */
@ControllerAdvice
public class GlobalExceptionAdvice {


    ExceptionCodeConfiguration codeConfiguration = new ExceptionCodeConfiguration();

    public GlobalExceptionAdvice(ExceptionCodeConfiguration codeConfiguration) {
        this.codeConfiguration = codeConfiguration;
    }

    //处理未知异常:模糊的非具体的返回前端，应以日志记录或打印到控制台
    @ExceptionHandler(value = Exception.class)
    @ResponseBody//由于要响应给前端，所以需要该注解
    public UnifyResponse handException(HttpServletRequest request, Exception e){
        StringBuffer url = request.getRequestURL();
        //模糊返回信息给前端。线上——记录日志，开发——控制台输出
        System.out.println(url);
        System.out.println(e);
        return new UnifyResponse(100500, "服务器内部异常", url.toString());
    }

    //处理已知异常:需要将错误信息返回给前端
    @ExceptionHandler(value = HttpException.class)
    @ResponseBody
    public UnifyResponse handHttpException(HttpServletRequest request, HttpException e){
        StringBuffer url = request.getRequestURL();
        System.out.println(url);
        System.out.println(e.getCode());
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:需要将错误信息返回给前端
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public UnifyResponse handNotFoundException(HttpServletRequest request, NotFoundException e){
        StringBuffer url = request.getRequestURL();
        System.out.println(url);
        System.out.println(e.getCode());
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:需要将错误信息返回给前端
    @ExceptionHandler(value = APIParamException.class)
    @ResponseBody
    public UnifyResponse handAPIParamException(HttpServletRequest request, APIParamException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:需要将错误信息返回给前端
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public UnifyResponse handMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e){
        StringBuffer url = request.getRequestURL();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String errStr = allErrors.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList())
                .toString();
        //需要将错误信息返回给前端
        return new UnifyResponse(400001,errStr,url.toString());
    }

    //处理已知异常:需要将错误信息返回给前端
    @ExceptionHandler(value = TokenAuthFailedException.class)
    @ResponseBody
    public UnifyResponse handTokenAuthFailedException(HttpServletRequest request, TokenAuthFailedException e){
        StringBuffer url = request.getRequestURL();
        System.out.println(url);
        System.out.println(e.getCode());
        //需要将错误信息返回给前端
        String ss = codeConfiguration.getMessage(e.getCode());
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:需要将错误信息返回给前端
    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseBody
    public UnifyResponse handForbiddenException(HttpServletRequest request, ForbiddenException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:新增、更新、删除成功
    @ExceptionHandler(value = SuccessException.class)
    @ResponseBody
    public UnifyResponse handSuccessException(HttpServletRequest request, SuccessException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:新增、更新、删除成功
    @ExceptionHandler(value = WechatUserExistException.class)
    @ResponseBody
    public UnifyResponse handWechatUserExistException(HttpServletRequest request, WechatUserExistException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:活动优惠券相关
    @ExceptionHandler(value = ActivityCouponException.class)
    @ResponseBody
    public UnifyResponse handActivityCouponException(HttpServletRequest request, ActivityCouponException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:商品相关
    @ExceptionHandler(value = SpuOrSkuException.class)
    @ResponseBody
    public UnifyResponse handSpuOrSkuException(HttpServletRequest request, SpuOrSkuException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

    //处理已知异常:订单相关
    @ExceptionHandler(value = OrderException.class)
    @ResponseBody
    public UnifyResponse handOrderException(HttpServletRequest request, OrderException e){
        StringBuffer url = request.getRequestURL();
        //需要将错误信息返回给前端
        return new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),url.toString());
    }

}