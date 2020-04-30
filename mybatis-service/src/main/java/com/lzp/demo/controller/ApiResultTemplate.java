// package com.lzp.demo.controller;
//
// import com.jd.wl.vsc.framework.domain.constant.ResultEnum;
// import com.jd.wl.vsc.framework.domain.vo.BaseListResponse;
// import com.jd.wl.vsc.framework.domain.vo.BaseObjectResponse;
//
// import java.util.List;
//
// /**
//  * Controller统一返回模板工具类
//  *
//  * @author lzp
//  * @version 1.0
//  * @date 2020/3/23.
//  */
// public class ApiResultTemplate {
//
//     public static <T> BaseListResponse success(List<T> t) {
//         return buildResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getResultMsg(), t);
//     }
//
//     public static <T> BaseListResponse success(String msg, List<T> t) {
//         return buildResponse(ResultEnum.SUCCESS.getCode(), msg, t);
//     }
//
//     public static <T> BaseListResponse buildResponse(int successFlag, String msg, List<T> t) {
//         BaseListResponse response = new BaseListResponse();
//         response.setSuccessFlag(successFlag);
//         response.setMsg(msg);
//         response.setResult(t);
//         return response;
//     }
//
//     public static <T> BaseObjectResponse success(T t) {
//         return buildResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getResultMsg(), t);
//     }
//
//     public static <T> BaseObjectResponse success(String msg, T t) {
//         return buildResponse(ResultEnum.SUCCESS.getCode(), msg, t);
//     }
//
//     public static <T> BaseObjectResponse buildResponse(int successFlag, String msg, T t) {
//         BaseListResponse response = new BaseListResponse();
//         response.setSuccessFlag(successFlag);
//         response.setMsg(msg);
//         response.setResult(t);
//         return response;
//     }
// }
